package io.coreflodev.dog.list.arch

import io.coreflodev.common.arch.Screen
import io.coreflodev.dog.list.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn

class ListScreen(
    private val listActionReducer: ListActionReducer,
    private val listUseCaseAggregator: ListUseCaseAggregator,
    private val listUiReducer: ListUiReducer,
    private val listNavigationReducer: ListNavigationReducer
): Screen<ListInput, ListOutput>() {

    override fun output(): Flow<ListOutput> =
        inputFlow
            .let(listActionReducer())
            .let(listUseCaseAggregator.execute(scope))
            .let(convertResultToOutput())

    private fun convertResultToOutput(): (Flow<Result>) -> Flow<ListOutput> =
        { stream ->
            val upstream = stream.shareIn(scope, SharingStarted.Lazily)

            merge(upstream.filterIsInstance<Result.UiUpdate>()
                .let(listUiReducer())
                .shareIn(scope, SharingStarted.Lazily, 1),
                upstream.filterIsInstance<Result.Navigation>()
                    .let(listNavigationReducer()))
        }
}
