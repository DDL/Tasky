package com.ddoczi.tasky.agenda.domain.usecases.home

import com.ddoczi.tasky.agenda.domain.usecases.event.DeleteEvent
import com.ddoczi.tasky.agenda.domain.usecases.reminder.DeleteReminder
import com.ddoczi.tasky.agenda.domain.usecases.task.DeleteTask

data class HomeUseCases(
    val deleteReminder: DeleteReminder,
    val deleteTask: DeleteTask,
    val deleteEvent: DeleteEvent
)