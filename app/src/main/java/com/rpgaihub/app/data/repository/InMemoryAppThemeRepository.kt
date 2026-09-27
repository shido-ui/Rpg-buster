package com.rpgaihub.app.data.repository

import com.rpgaihub.app.core.result.AppResult
import com.rpgaihub.app.domain.model.AppThemeMode
import com.rpgaihub.app.domain.repository.AppThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Phase 1 implementation of [AppThemeRepository].
 * Manages in-memory user preference with immediate reactivity.
 * Extension point for DataStore or Room persistence in subsequent phases.
 */
class InMemoryAppThemeRepository(
    initialMode: AppThemeMode = AppThemeMode.SYSTEM
) : AppThemeRepository {

    private val _themePreference = MutableStateFlow(initialMode)

    override fun getThemePreference(): Flow<AppThemeMode> = _themePreference.asStateFlow()

    override suspend fun setThemePreference(mode: AppThemeMode): AppResult<Unit> {
        _themePreference.value = mode
        return AppResult.success(Unit)
    }
}
