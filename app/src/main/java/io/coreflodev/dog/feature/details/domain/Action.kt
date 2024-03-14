package io.coreflodev.dog.feature.details.domain

sealed class Action {
    data object InitialAction : Action()
}
