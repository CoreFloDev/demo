package io.coreflodev.dog.common.arch

import kotlinx.coroutines.flow.Flow

data class Attach<I : ScreenInput, O : ScreenOutput>(
    val input: (I) -> Unit,
    val output: Flow<O>
)
