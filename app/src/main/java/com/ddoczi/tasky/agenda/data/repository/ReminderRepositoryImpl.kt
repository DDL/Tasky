package com.ddoczi.tasky.agenda.data.repository

import com.ddoczi.tasky.agenda.data.local.AgendaDao
import com.ddoczi.tasky.agenda.data.mapper.toReminderAgendaItem
import com.ddoczi.tasky.agenda.data.mapper.toReminderEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.ReminderRepository

class ReminderRepositoryImpl(private val agendaDao: AgendaDao) : ReminderRepository {
    override suspend fun getReminderById(id: String): AgendaItem.Reminder {
        return agendaDao.getReminderById(id).toReminderAgendaItem()
    }

    override suspend fun insertReminder(reminder: AgendaItem.Reminder) {
        agendaDao.insertReminder(reminder.toReminderEntity())
    }

    override suspend fun deleteReminderById(id: String) {
        agendaDao.deleteReminderById(id)
    }
}