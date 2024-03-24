package com.ddoczi.tasky.agenda.presentation.detail.agenda

import com.ddoczi.tasky.agenda.enums.AgendaType
import java.time.LocalDate
import java.time.LocalTime


data class AgendaDetailState(
    val id: String = "",
    val title: String = "",
    val fromDate: LocalDate = LocalDate.now(),
    val toDate: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val toTime: LocalTime = LocalTime.now(),
    val description: String = "",
    val agendaType: AgendaType = AgendaType.TASK,
    val isEditing: Boolean = false,
    val isTaskDone: Boolean = false,
    val shouldExit: Boolean = false,
    val reminder: String = "",
    val isSelectingReminderType: Boolean = false,
)