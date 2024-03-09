package com.ddoczi.tasky.agenda.presentation.home

sealed class HomeEvent {
    data object OnLogOut : HomeEvent()
    data object OnLogOutDismiss : HomeEvent()
}