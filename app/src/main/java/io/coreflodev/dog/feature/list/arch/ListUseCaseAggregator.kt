package io.coreflodev.dog.feature.list.arch

import io.coreflodev.dog.feature.list.domain.Action
import io.coreflodev.dog.feature.list.domain.DisplayDogListUseCase
import io.coreflodev.dog.feature.list.domain.OpenDogDetailsUseCase
import io.coreflodev.dog.feature.list.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn

class ListUseCaseAggregator(
    private val displayDogListUseCase: DisplayDogListUseCase,
    private val openDogDetailsUseCase: OpenDogDetailsUseCase,
) {

    fun execute(scope: CoroutineScope): (Flow<Action>) -> Flow<Result> = { stream ->
        val upstream = stream.shareIn(scope, SharingStarted.Eagerly, 1)

        listOf(
            upstream.filterIsInstance<Action.InitialAction>().let(displayDogListUseCase()),
            upstream.filterIsInstance<Action.OpenDetails>().let(openDogDetailsUseCase())
        )
            .merge()
    }
}
