package com.rpgaihub.app.domain.repository

import com.rpgaihub.app.core.result.AppResult
import com.rpgaihub.app.domain.model.RpgMode
import kotlinx.coroutines.flow.Flow

interface RpgModeRepository {
    fun getRpgModes(): Flow<List<RpgMode>>
    suspend fun getModeById(id: String): AppResult<RpgMode>
}
