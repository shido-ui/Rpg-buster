package com.rpgaihub.app.presentation.settings

import com.rpgaihub.app.domain.model.AppThemeMode

sealed interface SettingsEvent {
    data class SetThemeMode(val themeMode: AppThemeMode) : SettingsEvent
    data object ClearMessage : SettingsEvent
}
