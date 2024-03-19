package com.ddoczi.tasky.agenda.presentation.detail.task

import java.time.LocalDateTime

sealed class TaskDetailEvent {
    data object OnEdit : TaskDetailEvent()
    data object OnSave : TaskDetailEvent()
    data object OnClose : TaskDetailEvent()
    data object OnTaskDelete : TaskDetailEvent()
    data class OnDateSelected(val date: LocalDateTime) : TaskDetailEvent()
    data class OnTimeSelected(val time: LocalDateTime) : TaskDetailEvent()
}