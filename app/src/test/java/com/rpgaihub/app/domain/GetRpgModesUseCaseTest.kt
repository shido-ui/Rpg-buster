package com.rpgaihub.app.domain.usecase

import com.rpgaihub.app.data.repository.DefaultRpgModeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetRpgModesUseCaseTest {

    private val repository = DefaultRpgModeRepository()
    private val useCase = GetRpgModesUseCase(repository)

    @Test
    fun `usecase returns all three required phase 1 rpg modes`() = runBlocking {
        val modes = useCase().first()

        assertEquals(3, modes.size)
        val ids = modes.map { it.id }
        assertTrue(ids.contains("open_world_rpg"))
        assertTrue(ids.contains("characters"))
        assertTrue(ids.contains("rpg_and_characters"))
    }
}
