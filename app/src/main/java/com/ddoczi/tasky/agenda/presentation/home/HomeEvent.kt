package com.ddoczi.tasky.agenda.presentation.home

import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import java.time.LocalDate

sealed class HomeEvent {
    data object OnLogOutClick : HomeEvent()
    data object OnLogOutDismiss : HomeEvent()
    data class OnDateSelected(val date: LocalDate) : HomeEvent()
    data class OnDaySelected(val day: Int) : HomeEvent()
    data class OnItemClick(val agendaItem: AgendaItem) : HomeEvent()
    data class OnItemOptionsClick(val agendaItem: AgendaItem) : HomeEvent()
    data object OnItemOptionsDismiss : HomeEvent()
    data object OnAddAgendaClick : HomeEvent()
    object OnAgendaItemDismiss : HomeEvent()
    object OnRefreshAgenda : HomeEvent()
    data class OnDeleteItem(val agendaItem: AgendaItem) : HomeEvent()
}



