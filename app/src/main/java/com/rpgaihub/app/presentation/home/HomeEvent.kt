package com.rpgaihub.app.presentation.home

sealed interface HomeEvent {
    data object Refresh : HomeEvent
    data class ModeSelected(val modeId: String) : HomeEvent
}
