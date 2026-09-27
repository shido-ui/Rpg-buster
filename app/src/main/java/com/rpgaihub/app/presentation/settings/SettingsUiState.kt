package com.rpgaihub.app.presentation.settings

import com.rpgaihub.app.domain.model.AppThemeMode

data class SettingsUiState(
    val themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    val appVersion: String = "1.0.0-phase1",
    val buildPhase: String = "Phase 1: Android Foundation",
    val architectureSummary: String = "Clean Architecture (Presentation, Domain, Data, Core)",
    val isLoading: Boolean = false,
    val userMessage: String? = null
)
