package io.coreflodev.dog.feature.details.arch

import io.coreflodev.dog.feature.details.domain.Action
import io.coreflodev.dog.feature.details.domain.DisplayDogDetailsUseCase
import io.coreflodev.dog.feature.details.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.shareIn

class DetailsUseCaseAggregator(
    private val displayDogDetailsUseCase: DisplayDogDetailsUseCase
) {

    fun execute(scope: CoroutineScope): (Flow<Action>) -> Flow<Result> = { stream ->
        val upstream = stream.shareIn(scope, SharingStarted.Eagerly, 1)

        upstream.filterIsInstance<Action.InitialAction>().let(displayDogDetailsUseCase())
    }
}
