package com.rpgaihub.app.presentation

import com.rpgaihub.app.core.dispatchers.AppDispatchers
import com.rpgaihub.app.data.repository.DefaultRpgModeRepository
import com.rpgaihub.app.domain.usecase.GetRpgModesUseCase
import com.rpgaihub.app.presentation.home.HomeEvent
import com.rpgaihub.app.presentation.home.HomeViewModel
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
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private val testDispatchers = object : AppDispatchers {
        override val main: CoroutineDispatcher = testDispatcher
        override val io: CoroutineDispatcher = testDispatcher
        override val default: CoroutineDispatcher = testDispatcher
    }

    private val repository = DefaultRpgModeRepository()
    private val getRpgModesUseCase = GetRpgModesUseCase(repository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial load populates modes and clears loading state`() = runTest(testDispatcher) {
        val viewModel = HomeViewModel(getRpgModesUseCase, testDispatchers)

        // Advance coroutines
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
        assertEquals(3, state.modes.size)
        assertEquals("open_world_rpg", state.modes[0].id)
        assertEquals("characters", state.modes[1].id)
        assertEquals("rpg_and_characters", state.modes[2].id)
    }

    @Test
    fun `refresh event reloads modes successfully`() = runTest(testDispatcher) {
        val viewModel = HomeViewModel(getRpgModesUseCase, testDispatchers)
        advanceUntilIdle()

        viewModel.onEvent(HomeEvent.Refresh)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(3, state.modes.size)
    }
}
