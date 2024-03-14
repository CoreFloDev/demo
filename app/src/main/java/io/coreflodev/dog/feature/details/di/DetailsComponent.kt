package io.coreflodev.dog.feature.details.di

import io.coreflodev.dog.common.arch.Screen
import io.coreflodev.dog.common.di.AppComponent
import io.coreflodev.dog.feature.details.arch.DetailsInput
import io.coreflodev.dog.feature.details.arch.DetailsOutput
import me.tatarka.inject.annotations.Component

@DetailsScope
@Component
abstract class DetailsComponent(
    @Component val appComponent: AppComponent,
    imageId: String
) : DetailsModule(imageId) {
    abstract val screen: Screen<DetailsInput, DetailsOutput>
}
