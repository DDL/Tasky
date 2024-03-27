package com.ddoczi.tasky.agenda.presentation.detail.agenda

import com.ddoczi.tasky.agenda.enums.AgendaOption
import com.ddoczi.tasky.agenda.enums.AgendaType
import java.time.LocalDate
import java.time.LocalTime

sealed class AgendaDetailEvent {
    data object OnEdit : AgendaDetailEvent()
    data object OnSave : AgendaDetailEvent()
    data object OnClose : AgendaDetailEvent()
    data object OnDelete : AgendaDetailEvent()
    data class OnFromDateSelected(val fromDate: LocalDate) : AgendaDetailEvent()
    data class OnToDateSelected(val toDate: LocalDate) : AgendaDetailEvent()
    data class OnTimeSelected(val time: LocalTime) : AgendaDetailEvent()
    data class OnToTimeSelected(val toTime: LocalTime) : AgendaDetailEvent()
    data object OnReminderTypeClick : AgendaDetailEvent()
    data object OnReminderTypeDismiss : AgendaDetailEvent()
    data class OnReminderTypeSelect(val reminderType: String) : AgendaDetailEvent()
    data class OnUpdatedInformation(val title: String, val description: String) : AgendaDetailEvent()
    data class OnInitScreen(val agendaType: AgendaType, val agendaOption: AgendaOption?) : AgendaDetailEvent()
    data class OnOpenEditor(val title: String, val body: String) : AgendaDetailEvent()
}


