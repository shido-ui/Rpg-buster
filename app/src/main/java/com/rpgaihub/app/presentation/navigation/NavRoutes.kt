package com.rpgaihub.app.presentation.navigation

/**
 * Centralized navigation routes for RPG AI Hub.
 */
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Settings : NavRoutes("settings")
    object OpenWorldRpg : NavRoutes("open_world_rpg")
    object Characters : NavRoutes("characters")
    object RpgAndCharacters : NavRoutes("rpg_and_characters")
}
