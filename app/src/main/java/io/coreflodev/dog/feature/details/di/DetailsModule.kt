package io.coreflodev.dog.feature.details.di

import io.coreflodev.dog.common.arch.Screen
import io.coreflodev.dog.common.repo.DogRepository
import io.coreflodev.dog.feature.details.arch.DetailsActionReducer
import io.coreflodev.dog.feature.details.arch.DetailsInput
import io.coreflodev.dog.feature.details.arch.DetailsOutput
import io.coreflodev.dog.feature.details.arch.DetailsScreen
import io.coreflodev.dog.feature.details.arch.DetailsUiReducer
import io.coreflodev.dog.feature.details.arch.DetailsUseCaseAggregator
import io.coreflodev.dog.feature.details.domain.DisplayDogDetailsUseCase
import me.tatarka.inject.annotations.Provides

abstract class DetailsModule(private val imageId: String) {

    @Provides
    @DetailsScope
    protected fun provideScreen(
        displayDogDetailsUseCase: DisplayDogDetailsUseCase
    ): Screen<DetailsInput, DetailsOutput> =
        DetailsScreen(
            DetailsActionReducer(),
            DetailsUseCaseAggregator(displayDogDetailsUseCase),
            DetailsUiReducer()
        )

    @Provides
    @DetailsScope
    protected fun provideDisplayDogDetailsUseCase(repository: DogRepository) = DisplayDogDetailsUseCase(repository, imageId)
}
