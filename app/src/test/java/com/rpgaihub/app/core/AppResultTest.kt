package com.rpgaihub.app.core.result

import com.rpgaihub.app.core.error.AppError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AppResultTest {

    @Test
    fun `success result holds data and reports isSuccess`() {
        val result = AppResult.success("hero_data")

        assertTrue(result.isSuccess)
        assertFalse(result.isFailure)
        assertEquals("hero_data", result.getOrNull())
    }

    @Test
    fun `failure result holds error and reports isFailure`() {
        val error = AppError.NetworkError("Host unreachable", 503)
        val result = AppResult.failure(error)

        assertFalse(result.isSuccess)
        assertTrue(result.isFailure)
        assertNull(result.getOrNull())
        assertEquals("fallback", result.getOrElse { "fallback" })
    }

    @Test
    fun `map transforms success value and preserves failure`() {
        val successResult = AppResult.success(10)
        val mappedSuccess = successResult.map { it * 2 }
        assertEquals(20, mappedSuccess.getOrNull())

        val failResult: AppResult<Int> = AppResult.failure(AppError.UnknownError("failed"))
        val mappedFail = failResult.map { it * 2 }
        assertTrue(mappedFail.isFailure)
    }

    @Test
    fun `onSuccess and onFailure callbacks execute appropriately`() {
        var successCalled = false
        var failureCalled = false

        AppResult.success("valid")
            .onSuccess { successCalled = true }
            .onFailure { failureCalled = true }

        assertTrue(successCalled)
        assertFalse(failureCalled)

        successCalled = false
        AppResult.failure(AppError.SystemError("disk full"))
            .onSuccess { successCalled = true }
            .onFailure { failureCalled = true }

        assertFalse(successCalled)
        assertTrue(failureCalled)
    }
}
