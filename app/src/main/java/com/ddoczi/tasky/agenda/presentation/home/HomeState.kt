package com.ddoczi.tasky.agenda.presentation.home

import java.time.LocalDate

data class HomeState(
    val isLoggedOut: Boolean = false,
    val showLogout: Boolean = false,
    val currentDate: LocalDate = LocalDate.now(),
    val selectedDay: Int = 0,
)

