package com.rpgaihub.app.presentation

import com.rpgaihub.app.core.dispatchers.AppDispatchers
import com.rpgaihub.app.data.repository.InMemoryAppThemeRepository
import com.rpgaihub.app.domain.model.AppThemeMode
import com.rpgaihub.app.domain.usecase.GetThemePreferenceUseCase
import com.rpgaihub.app.domain.usecase.SetThemePreferenceUseCase
import com.rpgaihub.app.presentation.settings.SettingsEvent
import com.rpgaihub.app.presentation.settings.SettingsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private val testDispatchers = object : AppDispatchers {
        override val main: CoroutineDispatcher = testDispatcher
        override val io: CoroutineDispatcher = testDispatcher
        override val default: CoroutineDispatcher = testDispatcher
    }

    private val repository = InMemoryAppThemeRepository(initialMode = AppThemeMode.SYSTEM)
    private val getThemeUseCase = GetThemePreferenceUseCase(repository)
    private val setThemeUseCase = SetThemePreferenceUseCase(repository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state reflects system default theme`() = runTest(testDispatcher) {
        val viewModel = SettingsViewModel(getThemeUseCase, setThemeUseCase, testDispatchers)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(AppThemeMode.SYSTEM, state.themeMode)
        assertEquals("1.0.0-phase1", state.appVersion)
    }

    @Test
    fun `set theme event updates theme preference and uiState`() = runTest(testDispatcher) {
        val viewModel = SettingsViewModel(getThemeUseCase, setThemeUseCase, testDispatchers)
        advanceUntilIdle()

        viewModel.onEvent(SettingsEvent.SetThemeMode(AppThemeMode.DARK))
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(AppThemeMode.DARK, state.themeMode)
    }
}
