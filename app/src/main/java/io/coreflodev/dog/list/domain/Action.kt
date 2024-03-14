package io.coreflodev.dog.list.domain

sealed class Action {
    data object InitialAction : Action()
    data class OpenDetails(val id: String) : Action()
}
