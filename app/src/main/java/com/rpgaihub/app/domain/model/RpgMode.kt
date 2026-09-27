package com.rpgaihub.app.domain.model

import androidx.annotation.StringRes

/**
 * Domain model representing a top-level mode card in the RPG AI Hub.
 */
data class RpgMode(
    val id: String,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val tagRes: Int,
    val route: String,
    val phaseStatus: String,
    val plannedEngines: List<String>
)
