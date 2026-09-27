package com.rpgaihub.app.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.rpgaihub.app.domain.model.AppThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = ArcaneVioletPrimary,
    onPrimary = OnPrimaryDark,
    primaryContainer = ArcaneVioletDark,
    onPrimaryContainer = ArcaneVioletLight,
    secondary = CelestialCyanSecondary,
    onSecondary = OnPrimaryDark,
    secondaryContainer = CelestialCyanDark,
    onSecondaryContainer = CelestialCyanLight,
    tertiary = CrimsonRuneTertiary,
    onTertiary = OnPrimaryDark,
    tertiaryContainer = CrimsonRuneDark,
    onTertiaryContainer = CrimsonRuneLight,
    background = ObsidianBackgroundDark,
    onBackground = OnSurfaceDark,
    surface = AstralNavySurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = AstralNavySurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = AstralBorderDark
)

private val LightColorScheme = lightColorScheme(
    primary = DaylightPrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = ArcaneVioletLight,
    onPrimaryContainer = DaylightPrimaryLight,
    secondary = DaylightSecondaryLight,
    onSecondary = OnPrimaryLight,
    secondaryContainer = CelestialCyanLight,
    onSecondaryContainer = DaylightSecondaryLight,
    tertiary = DaylightTertiaryLight,
    onTertiary = OnPrimaryLight,
    tertiaryContainer = CrimsonRuneLight,
    onTertiaryContainer = DaylightTertiaryLight,
    background = DaylightBackgroundLight,
    onBackground = OnSurfaceLight,
    surface = DaylightSurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = DaylightSurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = DaylightBorderLight
)

@Composable
fun RpgAiHubTheme(
    themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                window.navigationBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = !darkTheme
                    isAppearanceLightNavigationBars = !darkTheme
                }
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = RpgTypography,
        shapes = RpgShapes,
        content = content
    )
}
