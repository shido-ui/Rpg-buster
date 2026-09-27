package com.rpgaihub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.rpgaihub.app.core.logging.AppLogger
import com.rpgaihub.app.data.repository.InMemoryAppThemeRepository
import com.rpgaihub.app.presentation.home.HomeViewModel
import com.rpgaihub.app.presentation.navigation.AppNavHost
import com.rpgaihub.app.presentation.settings.SettingsViewModel
import com.rpgaihub.app.presentation.theme.RpgAiHubTheme

class MainActivity : ComponentActivity() {

    private val themeRepository by lazy { InMemoryAppThemeRepository() }

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory
    }

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModel.provideFactory(themeRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppLogger.i(TAG, "Initializing RPG AI Hub Foundation (Phase 1)")
        enableEdgeToEdge()

        setContent {
            val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()

            RpgAiHubTheme(themeMode = settingsState.themeMode) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    AppNavHost(
                        navController = navController,
                        homeViewModel = homeViewModel,
                        settingsViewModel = settingsViewModel
                    )
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
