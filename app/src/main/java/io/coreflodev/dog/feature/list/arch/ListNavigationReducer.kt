package io.coreflodev.dog.feature.list.arch

import io.coreflodev.dog.common.nav.Navigation
import io.coreflodev.dog.feature.list.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

class ListNavigationReducer(private val nav: Navigation) {
    operator fun invoke(): (Flow<Result.Navigation>) -> Flow<ListOutput> = { stream ->
        stream.flatMapConcat { navigation ->
            flow {
                when (navigation) {
                    is Result.Navigation.OpenDetails -> nav.startDetailsActivity(navigation.id)
                }
            }
        }
    }
}
