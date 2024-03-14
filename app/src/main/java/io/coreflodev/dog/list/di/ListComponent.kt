package io.coreflodev.dog.list.di

import io.coreflodev.common.arch.Screen
import io.coreflodev.common.di.AppComponent
import io.coreflodev.dog.list.arch.ListInput
import io.coreflodev.dog.list.arch.ListOutput
import me.tatarka.inject.annotations.Component

@ListScope
@Component
abstract class ListComponent(
    @Component val appComponent: AppComponent
) : ListModule() {
    abstract val screen: Screen<ListInput, ListOutput>
}
