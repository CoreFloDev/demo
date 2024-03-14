package io.coreflodev.dog.feature.list.di

import io.coreflodev.dog.common.arch.Screen
import io.coreflodev.dog.common.di.AppComponent
import io.coreflodev.dog.feature.list.arch.ListInput
import io.coreflodev.dog.feature.list.arch.ListOutput
import me.tatarka.inject.annotations.Component

@ListScope
@Component
abstract class ListComponent(
    @Component val appComponent: AppComponent
) : ListModule() {
    abstract val screen: Screen<ListInput, ListOutput>
}
