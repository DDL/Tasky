package com.ddoczi.tasky.agenda.presentation.home

data class HomeState(
    val isLoggedOut: Boolean = false,
    val showLogout: Boolean = false,
    val agendaTypes: List<HomeAgendaType> = listOf(
        HomeAgendaType.Event,
        HomeAgendaType.Task,
        HomeAgendaType.Reminder
    ),
    val agendaItems: List<Unit> = emptyList(),
)