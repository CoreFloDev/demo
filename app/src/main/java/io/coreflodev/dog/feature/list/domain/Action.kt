package io.coreflodev.dog.feature.list.domain

sealed class Action {
    data object InitialAction : Action()
    data class OpenDetails(val id: String) : Action()
}
