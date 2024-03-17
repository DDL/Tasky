package com.ddoczi.tasky.agenda.domain.repository

import com.ddoczi.tasky.agenda.domain.model.AgendaItem

interface AgendaRepository {
    suspend fun getEventById(id: String): AgendaItem.Event
    suspend fun insertEvent(event: AgendaItem.Event)
    suspend fun deleteEventById(id: String)

    suspend fun getReminderById(id: String): AgendaItem.Reminder
    suspend fun insertReminder(reminder: AgendaItem.Reminder)
    suspend fun deleteReminderById(id: String)

    suspend fun getTaskById(id: String): AgendaItem.Task
    suspend fun insertTask(task: AgendaItem.Task)
    suspend fun deleteTaskById(id: String)
}