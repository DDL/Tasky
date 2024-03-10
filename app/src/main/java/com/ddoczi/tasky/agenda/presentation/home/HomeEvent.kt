package com.ddoczi.tasky.agenda.presentation.home

import java.time.LocalDate

sealed class HomeEvent {
    data object OnLogOut : HomeEvent()
    data object OnLogOutDismiss : HomeEvent()
    data class OnDateSelected(val date: LocalDate) : HomeEvent()

    data class OnDaySelected(val day: Int) : HomeEvent()
}