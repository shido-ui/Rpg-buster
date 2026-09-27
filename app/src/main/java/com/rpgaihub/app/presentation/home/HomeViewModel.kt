package com.rpgaihub.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rpgaihub.app.core.dispatchers.AppDispatchers
import com.rpgaihub.app.core.dispatchers.DefaultAppDispatchers
import com.rpgaihub.app.core.logging.AppLogger
import com.rpgaihub.app.data.repository.DefaultRpgModeRepository
import com.rpgaihub.app.domain.usecase.GetRpgModesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRpgModesUseCase: GetRpgModesUseCase,
    private val dispatchers: AppDispatchers = DefaultAppDispatchers()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadModes()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Refresh -> loadModes()
            is HomeEvent.ModeSelected -> {
                AppLogger.d(TAG, "Mode selected: ${event.modeId}")
            }
        }
    }

    private fun loadModes() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch(dispatchers.io) {
            getRpgModesUseCase()
                .catch { throwable ->
                    AppLogger.e(TAG, "Failed to load RPG modes", throwable)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Failed to load modes"
                        )
                    }
                }
                .collect { modesList ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            modes = modesList,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = DefaultRpgModeRepository()
                val useCase = GetRpgModesUseCase(repository)
                return HomeViewModel(useCase) as T
            }
        }
    }
}
