package com.ddoczi.tasky.agenda.presentation.detail.agenda

import java.time.LocalDate
import java.time.LocalTime

data class AgendaDetailState(
    val id: String = "",
    val title: String = "",
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val description: String = "",
    val isEditing: Boolean = true,
)