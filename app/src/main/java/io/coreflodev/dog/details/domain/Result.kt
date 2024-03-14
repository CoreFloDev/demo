package io.coreflodev.dog.details.domain
sealed class Result {
    sealed class UiUpdate : Result() {
        data object Loading : UiUpdate()
        data object Retry : UiUpdate()
        data class Display(
            val name: String,
            val image: String,
            val origin: String,
            val wikiUrl: String,
            val temperament: String
        ) : UiUpdate()
    }

    sealed class Navigation : Result()
}
