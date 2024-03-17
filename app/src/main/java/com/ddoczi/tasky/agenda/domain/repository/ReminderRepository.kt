package com.ddoczi.tasky.agenda.domain.repository

import com.ddoczi.tasky.agenda.domain.model.AgendaItem

interface ReminderRepository {
    suspend fun getReminderById(id: String): AgendaItem.Reminder
    suspend fun insertReminder(reminder: AgendaItem.Reminder)
    suspend fun deleteReminderById(id: String)
}