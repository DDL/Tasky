package com.ddoczi.tasky.agenda.presentation.home

import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.enums.AgendaOption
import com.ddoczi.tasky.agenda.enums.AgendaType
import java.time.LocalDate

sealed class HomeEvent {
    data object OnLogOutClick : HomeEvent()
    data object OnLogOutDismiss : HomeEvent()
    data object OnLogOutConfirm : HomeEvent()
    data class OnDateSelected(val date: LocalDate) : HomeEvent()
    data class OnDaySelected(val day: Int) : HomeEvent()
    data class OnItemClick(val agendaItem: AgendaItem) : HomeEvent()
    data class OnItemOptionsClick(val agendaItem: AgendaItem) : HomeEvent()
    data object OnItemOptionsDismiss : HomeEvent()
    data object OnAddAgendaClick : HomeEvent()
    data class OnRedirectToAddAgendaItem(val agendaType: AgendaType) : HomeEvent()
    data class OnRedirectToAgendaItem(val agendaItem: AgendaItem, val option: AgendaOption) : HomeEvent()
    data object OnAgendaItemDismiss : HomeEvent()
    data object OnRefreshAgenda : HomeEvent()
    data class OnDeleteItem(val agendaItem: AgendaItem) : HomeEvent()
}



