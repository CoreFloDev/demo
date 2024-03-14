package io.coreflodev.dog.details.domain

sealed class Action {
    data object InitialAction : Action()
}
