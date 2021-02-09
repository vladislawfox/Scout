package com.vladislawfox.scout.base.utils

object GlobalExceptionLogger {
    private var logger: ((Throwable) -> Unit)? = null

    fun setLogger(isDebug: Boolean, logger: (Throwable) -> Unit) {
        this.logger = { throwable ->
            if (isDebug) {
                throwable.printStackTrace()
            }
            logger.invoke(throwable)
        }
    }

    fun logException(throwable: Throwable) {
        logger?.invoke(throwable)
    }
}