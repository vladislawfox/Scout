package com.vladislawfox.scout.base.ui

import com.vladislawfox.scout.base.utils.GlobalExceptionLogger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal class CoroutineExecutor(
        dispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    private val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
        GlobalExceptionLogger.logException(throwable)
        handleException(throwable)
    }

    val scope = CoroutineScope(dispatcher + SupervisorJob() + exceptionHandler)

    /**LiveData for posting error to UI*/
    private val _error by lazy { MutableSharedFlow<AppError>() }
    val error by lazy { _error.asSharedFlow() }

    /**LiveData for showing progress on UI*/
    private val _progress by lazy { MutableStateFlow(false) }
    val progress by lazy { _progress.asStateFlow() }

    fun cancelScope() {
        scope.coroutineContext.cancelChildren()
    }

    fun runCoroutine(
            context: CoroutineContext = EmptyCoroutineContext,
            withProgress: Boolean = false,
            exceptionHandler: ((Exception) -> Boolean)? = null,
            block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch(context) {
            execute(withProgress, exceptionHandler, block)
        }
    }

    suspend fun execute(
            withProgress: Boolean,
            exceptionHandler: ((Exception) -> Boolean)?,
            block: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            if (withProgress) {
                _progress.value = true
            }
            try {
                block.invoke(this)
            } catch (e: Exception) {
                GlobalExceptionLogger.logException(e)
                if (exceptionHandler?.invoke(e) != false) {
                    handleException(e)
                }
            } finally {
                if (withProgress) {
                    _progress.value = false
                }
            }
        }
    }

    fun handleException(throwable: Throwable) {
        val appError = when (throwable) {
            is SocketTimeoutException -> TimeoutError(throwable)
            is UnknownHostException -> NoNetworkError(throwable)
            else -> OtherError(throwable)
        }
        _error.tryEmit(appError)
    }
}