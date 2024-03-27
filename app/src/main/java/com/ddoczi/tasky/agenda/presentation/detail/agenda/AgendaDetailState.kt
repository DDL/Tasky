package com.ddoczi.tasky.agenda.presentation.detail.agenda

import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.enums.AgendaType
import java.time.LocalDate
import java.time.LocalTime

data class AgendaDetailState(
    val id: String = "",
    val title: String = "New Agenda",
    val description: String = "Description",
    val agendaItem: AgendaItem? = null,
    val fromDate: LocalDate = LocalDate.now(),
    val toDate: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val toTime: LocalTime = LocalTime.now(),
    val agendaType: AgendaType = AgendaType.TASK,
    val isEditing: Boolean = false,
    val isEditable: Boolean = true,
    val isTaskDone: Boolean = false,
    val shouldExit: Boolean = false,
    val reminder: String = "",
    val isSelectingReminderType: Boolean = false,
)