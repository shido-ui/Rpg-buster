package com.rpgaihub.app.domain.repository

import com.rpgaihub.app.core.result.AppResult
import com.rpgaihub.app.domain.model.AppThemeMode
import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    fun getThemePreference(): Flow<AppThemeMode>
    suspend fun setThemePreference(mode: AppThemeMode): AppResult<Unit>
}
