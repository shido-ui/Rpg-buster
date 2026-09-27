package com.rpgaihub.app.domain.usecase

import com.rpgaihub.app.core.result.AppResult
import com.rpgaihub.app.domain.model.AppThemeMode
import com.rpgaihub.app.domain.repository.AppThemeRepository

class SetThemePreferenceUseCase(
    private val repository: AppThemeRepository
) {
    suspend operator fun invoke(mode: AppThemeMode): AppResult<Unit> =
        repository.setThemePreference(mode)
}
