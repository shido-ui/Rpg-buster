package com.rpgaihub.app.core.logging

import org.junit.Assert.assertEquals
import org.junit.Test

class AppLoggerTest {

    private class RecordingLogger : Logger {
        val records = mutableListOf<String>()

        override fun d(tag: String, message: String, throwable: Throwable?) {
            records.add("D:$tag:$message")
        }

        override fun i(tag: String, message: String, throwable: Throwable?) {
            records.add("I:$tag:$message")
        }

        override fun w(tag: String, message: String, throwable: Throwable?) {
            records.add("W:$tag:$message")
        }

        override fun e(tag: String, message: String, throwable: Throwable?) {
            records.add("E:$tag:$message")
        }
    }

    @Test
    fun `logger delegates to active logger when level allows`() {
        val recordingLogger = RecordingLogger()
        AppLogger.setLogger(recordingLogger)
        AppLogger.minLogLevel = AppLogger.LogLevel.DEBUG

        AppLogger.d("Tag1", "Debug message")
        AppLogger.i("Tag2", "Info message")
        AppLogger.w("Tag3", "Warn message")
        AppLogger.e("Tag4", "Error message")

        assertEquals(4, recordingLogger.records.size)
        assertEquals("D:Tag1:Debug message", recordingLogger.records[0])
        assertEquals("I:Tag2:Info message", recordingLogger.records[1])
        assertEquals("W:Tag3:Warn message", recordingLogger.records[2])
        assertEquals("E:Tag4:Error message", recordingLogger.records[3])
    }

    @Test
    fun `logger filters out messages below minimum level`() {
        val recordingLogger = RecordingLogger()
        AppLogger.setLogger(recordingLogger)
        AppLogger.minLogLevel = AppLogger.LogLevel.WARN

        AppLogger.d("Tag1", "Debug should be ignored")
        AppLogger.i("Tag2", "Info should be ignored")
        AppLogger.w("Tag3", "Warn should pass")
        AppLogger.e("Tag4", "Error should pass")

        assertEquals(2, recordingLogger.records.size)
        assertEquals("W:Tag3:Warn should pass", recordingLogger.records[0])
        assertEquals("E:Tag4:Error should pass", recordingLogger.records[1])
    }
}
