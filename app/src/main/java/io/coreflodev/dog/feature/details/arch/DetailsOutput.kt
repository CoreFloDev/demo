package io.coreflodev.dog.feature.details.arch

import io.coreflodev.dog.common.arch.ScreenOutput

data class DetailsOutput(val uiState: UiState = UiState.Loading) : ScreenOutput

sealed class UiState {
    data object Loading : UiState()
    data object Retry : UiState()

    data class Display(
        val name: String,
        val image: String,
        val origin: String,
        val wikiUrl: String,
        val temperament: String
    ) : UiState()
}
