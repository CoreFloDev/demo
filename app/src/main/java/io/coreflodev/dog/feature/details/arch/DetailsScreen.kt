package io.coreflodev.dog.feature.details.arch

import io.coreflodev.dog.common.arch.Screen
import io.coreflodev.dog.feature.details.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.shareIn

class DetailsScreen(
    private val detailsActionReducer: DetailsActionReducer,
    private val detailsUseCaseAggregator: DetailsUseCaseAggregator,
    private val detailsUiReducer: DetailsUiReducer
) : Screen<DetailsInput, DetailsOutput>() {

    override fun output(): Flow<DetailsOutput> =
        inputFlow
            .let(detailsActionReducer())
            .let(detailsUseCaseAggregator.execute(scope))
            .let(convertResultToOutput())

    private fun convertResultToOutput(): (Flow<Result>) -> Flow<DetailsOutput> =
        { stream ->
            val upstream = stream.shareIn(scope, SharingStarted.Lazily)

            upstream.filterIsInstance<Result.UiUpdate>()
                .let(detailsUiReducer())
                .shareIn(scope, SharingStarted.Lazily, 1)
        }
}
