package com.ddoczi.tasky.agenda.presentation.home

import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import java.time.LocalDate

data class HomeState(
    val isLoggedOut: Boolean = false,
    val showLogout: Boolean = false,
    val currentDate: LocalDate = LocalDate.now(),
    val selectedDay: Int = 0,
    val profileName: String = "",
    val agendaItems: List<AgendaItem> = emptyList(),
    val agendaTypes: List<HomeAgendaType> = listOf(
        HomeAgendaType.Event,
        HomeAgendaType.Task,
        HomeAgendaType.Reminder
    ),
    val selectedAgendaType: HomeAgendaType? = null,
    val selectedAgendaItem: AgendaItem? = null,
    val showAgendaOptions: Boolean = false,
    val showItemOptions: Boolean = false,
)

