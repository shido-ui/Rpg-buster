package com.rpgaihub.app.core.logging

import android.util.Log

/**
 * Structured logging abstraction for the RPG AI Hub application.
 *
 * Supports interchangeable log trees to suppress or route logs in release builds,
 * testing harnesses, or remote diagnostics.
 */
interface Logger {
    fun d(tag: String, message: String, throwable: Throwable? = null)
    fun i(tag: String, message: String, throwable: Throwable? = null)
    fun w(tag: String, message: String, throwable: Throwable? = null)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}

object AppLogger : Logger {

    enum class LogLevel {
        DEBUG, INFO, WARN, ERROR, NONE
    }

    private var activeLogger: Logger = AndroidDefaultLogger()
    var minLogLevel: LogLevel = LogLevel.DEBUG

    fun setLogger(logger: Logger) {
        activeLogger = logger
    }

    override fun d(tag: String, message: String, throwable: Throwable?) {
        if (minLogLevel <= LogLevel.DEBUG) {
            activeLogger.d(tag, message, throwable)
        }
    }

    override fun i(tag: String, message: String, throwable: Throwable?) {
        if (minLogLevel <= LogLevel.INFO) {
            activeLogger.i(tag, message, throwable)
        }
    }

    override fun w(tag: String, message: String, throwable: Throwable?) {
        if (minLogLevel <= LogLevel.WARN) {
            activeLogger.w(tag, message, throwable)
        }
    }

    override fun e(tag: String, message: String, throwable: Throwable?) {
        if (minLogLevel <= LogLevel.ERROR) {
            activeLogger.e(tag, message, throwable)
        }
    }

    class AndroidDefaultLogger : Logger {
        override fun d(tag: String, message: String, throwable: Throwable?) {
            Log.d(tag, message, throwable)
        }

        override fun i(tag: String, message: String, throwable: Throwable?) {
            Log.i(tag, message, throwable)
        }

        override fun w(tag: String, message: String, throwable: Throwable?) {
            Log.w(tag, message, throwable)
        }

        override fun e(tag: String, message: String, throwable: Throwable?) {
            Log.e(tag, message, throwable)
        }
    }

    class NoOpLogger : Logger {
        override fun d(tag: String, message: String, throwable: Throwable?) = Unit
        override fun i(tag: String, message: String, throwable: Throwable?) = Unit
        override fun w(tag: String, message: String, throwable: Throwable?) = Unit
        override fun e(tag: String, message: String, throwable: Throwable?) = Unit
    }
}
