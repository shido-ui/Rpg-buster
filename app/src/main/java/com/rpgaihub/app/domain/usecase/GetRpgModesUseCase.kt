package com.rpgaihub.app.domain.usecase

import com.rpgaihub.app.domain.model.RpgMode
import com.rpgaihub.app.domain.repository.RpgModeRepository
import kotlinx.coroutines.flow.Flow

class GetRpgModesUseCase(
    private val repository: RpgModeRepository
) {
    operator fun invoke(): Flow<List<RpgMode>> = repository.getRpgModes()
}
