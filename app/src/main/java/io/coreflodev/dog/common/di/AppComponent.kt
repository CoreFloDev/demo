package io.coreflodev.dog.common.di

import io.coreflodev.dog.common.nav.Navigation
import io.coreflodev.dog.common.repo.DogRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AppScope
@Component
abstract class AppComponent(
    @get:Provides override val nav: () -> Navigation
) : AppModule(nav) {

    abstract val movieRepo: DogRepository

    abstract val navigation: Navigation
}
