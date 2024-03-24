package com.ddoczi.tasky.agenda.presentation.home

import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import java.time.LocalDate
import java.time.LocalDateTime

data class HomeState(
    val isLoggedOut: Boolean = false,
    val showLogout: Boolean = false,
    val currentDate: LocalDate = LocalDate.now(),
    val selectedDay: Int = 0,
    val profileName: String = "",
    val agendaItems: List<AgendaItem> =  emptyList(),
    val selectedAgendaItem: AgendaItem? = null,
    val showAgendaOptions: Boolean = false,
    val showItemOptions: Boolean = false,
)

