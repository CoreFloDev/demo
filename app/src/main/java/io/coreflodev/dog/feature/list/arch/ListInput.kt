package io.coreflodev.dog.feature.list.arch

import io.coreflodev.dog.common.arch.ScreenInput

sealed class ListInput : ScreenInput {
    data class PictureClicked(val id: String) : ListInput()
    data object RetryClicked : ListInput()
}
