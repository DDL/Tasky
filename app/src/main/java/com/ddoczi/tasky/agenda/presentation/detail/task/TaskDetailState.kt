package com.ddoczi.tasky.agenda.presentation.detail.task

import java.time.LocalDate
import java.time.LocalTime

data class TaskDetailState(
    val id: String = "",
    val title: String = "",
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val description: String = "",
    val isEditing: Boolean = true,
    val isDone: Boolean = false
)