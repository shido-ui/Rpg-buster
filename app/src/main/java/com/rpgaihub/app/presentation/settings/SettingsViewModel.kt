package com.rpgaihub.app.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rpgaihub.app.core.dispatchers.AppDispatchers
import com.rpgaihub.app.core.dispatchers.DefaultAppDispatchers
import com.rpgaihub.app.core.logging.AppLogger
import com.rpgaihub.app.core.result.onFailure
import com.rpgaihub.app.core.result.onSuccess
import com.rpgaihub.app.data.repository.InMemoryAppThemeRepository
import com.rpgaihub.app.domain.model.AppThemeMode
import com.rpgaihub.app.domain.repository.AppThemeRepository
import com.rpgaihub.app.domain.usecase.GetThemePreferenceUseCase
import com.rpgaihub.app.domain.usecase.SetThemePreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getThemePreferenceUseCase: GetThemePreferenceUseCase,
    private val setThemePreferenceUseCase: SetThemePreferenceUseCase,
    private val dispatchers: AppDispatchers = DefaultAppDispatchers()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        observeThemePreference()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetThemeMode -> updateThemeMode(event.themeMode)
            is SettingsEvent.ClearMessage -> _uiState.update { it.copy(userMessage = null) }
        }
    }

    private fun observeThemePreference() {
        viewModelScope.launch(dispatchers.io) {
            getThemePreferenceUseCase().collect { mode ->
                AppLogger.d(TAG, "Theme preference observed: $mode")
                _uiState.update { it.copy(themeMode = mode) }
            }
        }
    }

    private fun updateThemeMode(mode: AppThemeMode) {
        viewModelScope.launch(dispatchers.io) {
            AppLogger.i(TAG, "Changing theme preference to: $mode")
            setThemePreferenceUseCase(mode)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            themeMode = mode,
                            userMessage = "Theme updated"
                        )
                    }
                }
                .onFailure { error ->
                    AppLogger.e(TAG, "Failed to update theme mode", error.cause)
                    _uiState.update {
                        it.copy(userMessage = "Failed to update theme: ${error.message}")
                    }
                }
        }
    }

    companion object {
        private const val TAG = "SettingsViewModel"

        fun provideFactory(repository: AppThemeRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val getThemeUseCase = GetThemePreferenceUseCase(repository)
                    val setThemeUseCase = SetThemePreferenceUseCase(repository)
                    return SettingsViewModel(getThemeUseCase, setThemeUseCase) as T
                }
            }
    }
}
