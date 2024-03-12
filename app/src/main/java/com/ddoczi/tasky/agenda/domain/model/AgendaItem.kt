package com.ddoczi.tasky.agenda.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

sealed class AgendaItem(
    val agendaItemId: String,
    val title: String,
    val description: String,
    val time: LocalDate,
    val remindAt: LocalDateTime
){
    data class Event(
        val eventId: String,
        val eventTitle: String,
        val eventDescription: String,
        val eventFromDate: LocalDate,
        val eventToDate: LocalDate,
        val eventRemindAt: LocalDateTime
    ) : AgendaItem(
        agendaItemId = eventId,
        title = eventTitle,
        description = eventDescription,
        time = eventFromDate,
        remindAt = eventRemindAt
    )

    data class Task(
        val taskId: String,
        val taskTitle: String,
        val taskDescription: String,
        val taskDate: LocalDate,
        val taskRemindAt: LocalDateTime,
        val isDone: Boolean
    ) : AgendaItem(
        agendaItemId = taskId,
        title = taskTitle,
        description = taskDescription,
        time = taskDate,
        remindAt = taskRemindAt
    )

    data class Reminder(
        val reminderId: String,
        val reminderTitle: String,
        val reminderDescription: String,
        val reminderDate: LocalDate,
        val reminderRemindAt: LocalDateTime
    ) : AgendaItem(
        agendaItemId = reminderId,
        title = reminderTitle,
        description = reminderDescription,
        time = reminderDate,
        remindAt = reminderRemindAt)

}