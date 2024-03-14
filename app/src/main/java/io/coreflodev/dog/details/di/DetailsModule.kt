package io.coreflodev.dog.details.di

import io.coreflodev.common.arch.Screen
import io.coreflodev.common.repo.DogRepository
import io.coreflodev.dog.details.arch.DetailsActionReducer
import io.coreflodev.dog.details.arch.DetailsInput
import io.coreflodev.dog.details.arch.DetailsOutput
import io.coreflodev.dog.details.arch.DetailsScreen
import io.coreflodev.dog.details.arch.DetailsUiReducer
import io.coreflodev.dog.details.arch.DetailsUseCaseAggregator
import io.coreflodev.dog.details.domain.DisplayDogDetailsUseCase
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
