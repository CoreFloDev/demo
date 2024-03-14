package io.coreflodev.dog.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import io.coreflodev.dog.common.arch.Attach
import io.coreflodev.dog.common.arch.ScreenInput
import io.coreflodev.dog.common.arch.ScreenOutput
import io.coreflodev.dog.common.theme.DogApiTheme

@Composable
fun <I : ScreenInput, O : ScreenOutput> AndroidView(
    attach: Attach<I, O>,
    ui: @Composable (O, (I) -> Unit) -> Unit
) {
    DogApiTheme {
        val (input, output) = attach

        val state = output.collectAsState(null)

        state.value?.let {
            ui.invoke(it, input)
        }
    }
}
