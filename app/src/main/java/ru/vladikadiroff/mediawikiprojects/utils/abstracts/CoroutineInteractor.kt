package ru.vladikadiroff.mediawikiprojects.utils.abstracts

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class CoroutineInteractor {

    private var interactorJob = Job()
    private val interactorScope = CoroutineScope(Dispatchers.Main + interactorJob)

    fun <P> CoroutineInteractor.launchBackground(action: suspend CoroutineScope.() -> P) {
        launchCoroutine(action, interactorScope, Dispatchers.IO)
    }

    private inline fun <P> launchCoroutine(
        crossinline action: suspend CoroutineScope.() -> P,
        coroutineScope: CoroutineScope,
        coroutineContext: CoroutineContext
    ) {
        coroutineScope.launch {
            withContext(coroutineContext) {
                action.invoke(this)
            }
        }
    }

    fun onDestroy(){
        interactorScope.cancel()
    }

}