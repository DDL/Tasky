package com.ddoczi.tasky.agenda.presentation.home

import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import java.time.LocalDate
import java.time.LocalDateTime

data class HomeState(
    val isLoggedOut: Boolean = false,
    val showLogout: Boolean = false,
    val currentDate: LocalDate = LocalDate.now(),
    val selectedDay: Int = 0,
    val profileName: String = "",
    val agendaItems: List<AgendaItem> =  listOf(
        AgendaItem.Event(
            eventId = "1",
            eventTitle = "Event 1",
            eventDescription = "Event 1 description",
            eventFromDate = LocalDateTime.now(),
            eventToDate = LocalDateTime.now(),
            eventRemindAt = LocalDateTime.now()
        ),
        AgendaItem.Task(
            taskId = "1",
            taskTitle = "Task 1",
            taskDescription = "Task 1 description",
            taskDate = LocalDateTime.now(),
            taskRemindAt = LocalDateTime.now(),
            isDone = false
        ),
        AgendaItem.Reminder(
            reminderId = "1",
            reminderTitle = "Reminder 1",
            reminderDescription = "Reminder 1 description",
            reminderDate = LocalDateTime.now(),
            reminderRemindAt = LocalDateTime.now()
        )
    ),
    val selectedAgendaItem: AgendaItem? = null,
    val showAgendaOptions: Boolean = false,
    val showItemOptions: Boolean = false,
)

