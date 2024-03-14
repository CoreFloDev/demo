package io.coreflodev.dog.common.arch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

abstract class Screen<I : ScreenInput, O : ScreenOutput> {

    private var viewScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    protected val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val input: Channel<I> = Channel()
    protected val inputFlow = input.receiveAsFlow()

    private val output by lazy { output() }

    protected abstract fun output(): Flow<O>
    fun terminate() {
        scope.cancel()
    }

    fun attach(): Attach<I, O> {
        viewScope = CoroutineScope(Dispatchers.IO)

        return Attach(
            output = output,
            input = { data -> viewScope.launch { input.send(data) } }
        )
    }

    fun detach() {
        viewScope.cancel()
    }
}
