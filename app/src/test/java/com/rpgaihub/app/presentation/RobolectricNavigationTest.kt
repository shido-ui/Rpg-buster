package com.rpgaihub.app.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rpgaihub.app.presentation.navigation.NavRoutes
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class RobolectricNavigationTest {

    @Test
    fun `navigation routes have expected distinct route identifiers`() {
        val routes = listOf(
            NavRoutes.Home.route,
            NavRoutes.Settings.route,
            NavRoutes.OpenWorldRpg.route,
            NavRoutes.Characters.route,
            NavRoutes.RpgAndCharacters.route
        )

        assertEquals("home", NavRoutes.Home.route)
        assertEquals("settings", NavRoutes.Settings.route)
        assertEquals("open_world_rpg", NavRoutes.OpenWorldRpg.route)
        assertEquals("characters", NavRoutes.Characters.route)
        assertEquals("rpg_and_characters", NavRoutes.RpgAndCharacters.route)

        // Ensure all routes are unique
        assertEquals(routes.size, routes.distinct().size)
    }
}
