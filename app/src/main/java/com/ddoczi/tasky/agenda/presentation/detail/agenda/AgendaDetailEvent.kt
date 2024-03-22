package com.ddoczi.tasky.agenda.presentation.detail.agenda

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class AgendaDetailEvent {
    data object OnEdit : AgendaDetailEvent()
    data object OnSave : AgendaDetailEvent()
    data object OnClose : AgendaDetailEvent()
    data object OnDelete : AgendaDetailEvent()
    data class OnDateSelected(val date: LocalDateTime) : AgendaDetailEvent()
    data class OnTimeSelected(val time: LocalDateTime) : AgendaDetailEvent()
    data class OnFromDateSelected(val date: LocalDate) : AgendaDetailEvent()
    data class OnFromTimeSelected(val time: LocalTime) : AgendaDetailEvent()
    data object OnReminderTypeClick : AgendaDetailEvent()
    data object OnReminderTypeDismiss : AgendaDetailEvent()
    data object OnReminderTypeSelect : AgendaDetailEvent()
    data class OnUpdatedInformation(val title: String, val description: String) : AgendaDetailEvent()
}


