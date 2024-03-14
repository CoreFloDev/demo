package io.coreflodev.dog.feature.list.di

import io.coreflodev.dog.common.arch.Screen
import io.coreflodev.dog.common.nav.Navigation
import io.coreflodev.dog.common.repo.DogRepository
import io.coreflodev.dog.feature.list.arch.ListActionReducer
import io.coreflodev.dog.feature.list.arch.ListInput
import io.coreflodev.dog.feature.list.arch.ListNavigationReducer
import io.coreflodev.dog.feature.list.arch.ListOutput
import io.coreflodev.dog.feature.list.arch.ListScreen
import io.coreflodev.dog.feature.list.arch.ListUiReducer
import io.coreflodev.dog.feature.list.arch.ListUseCaseAggregator
import io.coreflodev.dog.feature.list.domain.DisplayDogListUseCase
import io.coreflodev.dog.feature.list.domain.OpenDogDetailsUseCase
import me.tatarka.inject.annotations.Provides

abstract class ListModule {
    @Provides
    @ListScope
    protected fun provideScreen(
        displayDogListUseCase: DisplayDogListUseCase,
        openDogDetailsUseCase: OpenDogDetailsUseCase,
        navigation: Navigation
    ): Screen<ListInput, ListOutput> = ListScreen(
        ListActionReducer(),
        ListUseCaseAggregator(displayDogListUseCase, openDogDetailsUseCase),
        ListUiReducer(),
        ListNavigationReducer(navigation)
    )

    @Provides
    @ListScope
    protected fun provideDisplayDogListUseCase(repo: DogRepository) = DisplayDogListUseCase(repo)

    @Provides
    @ListScope
    protected fun provideOpenDogDetailsUseCase() = OpenDogDetailsUseCase()
}
