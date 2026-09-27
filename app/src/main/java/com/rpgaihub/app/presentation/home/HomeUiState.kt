package com.rpgaihub.app.presentation.home

import com.rpgaihub.app.domain.model.RpgMode

data class HomeUiState(
    val isLoading: Boolean = false,
    val modes: List<RpgMode> = emptyList(),
    val errorMessage: String? = null
)
