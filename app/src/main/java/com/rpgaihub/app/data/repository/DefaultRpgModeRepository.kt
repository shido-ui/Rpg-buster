package com.rpgaihub.app.data.repository

import com.rpgaihub.app.R
import com.rpgaihub.app.core.error.AppError
import com.rpgaihub.app.core.result.AppResult
import com.rpgaihub.app.domain.model.RpgMode
import com.rpgaihub.app.domain.repository.RpgModeRepository
import com.rpgaihub.app.presentation.navigation.NavRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Phase 1 repository supplying top-level mode definitions for the RPG AI Hub.
 */
class DefaultRpgModeRepository : RpgModeRepository {

    private val modes = listOf(
        RpgMode(
            id = "open_world_rpg",
            titleRes = R.string.mode_open_world_title,
            subtitleRes = R.string.mode_open_world_subtitle,
            descriptionRes = R.string.mode_open_world_desc,
            tagRes = R.string.mode_open_world_tag,
            route = NavRoutes.OpenWorldRpg.route,
            phaseStatus = "Under Development",
            plannedEngines = listOf(
                "Autonomous World Simulation",
                "Reactive Environment & Lore Engine",
                "Dynamic Event Dispatcher",
                "Spatial State Persistence"
            )
        ),
        RpgMode(
            id = "characters",
            titleRes = R.string.mode_characters_title,
            subtitleRes = R.string.mode_characters_subtitle,
            descriptionRes = R.string.mode_characters_desc,
            tagRes = R.string.mode_characters_tag,
            route = NavRoutes.Characters.route,
            phaseStatus = "Under Development",
            plannedEngines = listOf(
                "Persona & Motivation Hierarchy",
                "Multi-Tier Working & Episodic Memory",
                "Psychological State Evaluator",
                "Dynamic Dialogue Generation"
            )
        ),
        RpgMode(
            id = "rpg_and_characters",
            titleRes = R.string.mode_rpg_characters_title,
            subtitleRes = R.string.mode_rpg_characters_subtitle,
            descriptionRes = R.string.mode_rpg_characters_desc,
            tagRes = R.string.mode_rpg_characters_tag,
            route = NavRoutes.RpgAndCharacters.route,
            phaseStatus = "Under Development",
            plannedEngines = listOf(
                "Full World-Persona Fusion Engine",
                "Multi-Agent Party Dynamics & Banter",
                "Emergent Relationship Engine",
                "Adaptive Quest & Narrative Rerouting"
            )
        )
    )

    override fun getRpgModes(): Flow<List<RpgMode>> = flowOf(modes)

    override suspend fun getModeById(id: String): AppResult<RpgMode> {
        val found = modes.firstOrNull { it.id == id }
        return if (found != null) {
            AppResult.success(found)
        } else {
            AppResult.failure(AppError.SystemError("Mode with id '$id' not found"))
        }
    }
}
