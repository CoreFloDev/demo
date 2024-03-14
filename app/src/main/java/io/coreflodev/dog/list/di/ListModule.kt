package io.coreflodev.dog.list.di

import io.coreflodev.common.arch.Screen
import io.coreflodev.common.nav.Navigation
import io.coreflodev.common.repo.DogRepository
import io.coreflodev.dog.list.arch.ListActionReducer
import io.coreflodev.dog.list.arch.ListInput
import io.coreflodev.dog.list.arch.ListNavigationReducer
import io.coreflodev.dog.list.arch.ListOutput
import io.coreflodev.dog.list.arch.ListScreen
import io.coreflodev.dog.list.arch.ListUiReducer
import io.coreflodev.dog.list.arch.ListUseCaseAggregator
import io.coreflodev.dog.list.domain.DisplayDogListUseCase
import io.coreflodev.dog.list.domain.OpenDogDetailsUseCase
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
