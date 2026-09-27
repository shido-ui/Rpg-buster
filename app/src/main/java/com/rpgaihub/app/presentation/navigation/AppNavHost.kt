package com.rpgaihub.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rpgaihub.app.R
import com.rpgaihub.app.presentation.home.HomeScreen
import com.rpgaihub.app.presentation.home.HomeViewModel
import com.rpgaihub.app.presentation.placeholder.PlaceholderScreen
import com.rpgaihub.app.presentation.settings.SettingsScreen
import com.rpgaihub.app.presentation.settings.SettingsViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
    startDestination: String = NavRoutes.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToMode = { route ->
                    navController.navigate(route)
                },
                onNavigateToSettings = {
                    navController.navigate(NavRoutes.Settings.route)
                }
            )
        }

        composable(NavRoutes.Settings.route) {
            SettingsScreen(
                viewModel = settingsViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.OpenWorldRpg.route) {
            PlaceholderScreen(
                title = stringResource(R.string.mode_open_world_title),
                subtitle = stringResource(R.string.mode_open_world_subtitle),
                description = stringResource(R.string.mode_open_world_desc),
                plannedEngines = listOf(
                    "Autonomous World Simulation Engine",
                    "Dynamic Event & Environmental Logic",
                    "Spatial Memory & Node Graph",
                    "Reactive Lore Resolver"
                ),
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.Characters.route) {
            PlaceholderScreen(
                title = stringResource(R.string.mode_characters_title),
                subtitle = stringResource(R.string.mode_characters_subtitle),
                description = stringResource(R.string.mode_characters_desc),
                plannedEngines = listOf(
                    "Persona Core & Motive Engine",
                    "Tiered Working & Episodic Memory",
                    "Psychological State Evaluator",
                    "Context-Aware Neural Dialogue Dispatcher"
                ),
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.RpgAndCharacters.route) {
            PlaceholderScreen(
                title = stringResource(R.string.mode_rpg_characters_title),
                subtitle = stringResource(R.string.mode_rpg_characters_subtitle),
                description = stringResource(R.string.mode_rpg_characters_desc),
                plannedEngines = listOf(
                    "Full World-Persona Fusion Engine",
                    "Multi-Agent Party Dynamics & Banter",
                    "Emergent Relationship Engine",
                    "Dynamic Quest Narrative Rerouter"
                ),
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
