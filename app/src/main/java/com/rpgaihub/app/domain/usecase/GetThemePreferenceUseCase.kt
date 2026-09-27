package com.rpgaihub.app.domain.usecase

import com.rpgaihub.app.domain.model.AppThemeMode
import com.rpgaihub.app.domain.repository.AppThemeRepository
import kotlinx.coroutines.flow.Flow

class GetThemePreferenceUseCase(
    private val repository: AppThemeRepository
) {
    operator fun invoke(): Flow<AppThemeMode> = repository.getThemePreference()
}
