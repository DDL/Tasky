package com.ddoczi.tasky.agenda.data

import com.ddoczi.tasky.agenda.data.local.AgendaDao
import com.ddoczi.tasky.agenda.data.mapper.toEventAgendaItem
import com.ddoczi.tasky.agenda.data.mapper.toEventEntity
import com.ddoczi.tasky.agenda.data.mapper.toReminderAgendaItem
import com.ddoczi.tasky.agenda.data.mapper.toReminderEntity
import com.ddoczi.tasky.agenda.data.mapper.toTaskAgendaItem
import com.ddoczi.tasky.agenda.data.mapper.toTaskEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.AgendaRepository

class AgendaRepositoryImpl(private val agendaDao: AgendaDao) : AgendaRepository {
    override suspend fun getEventById(id: String): AgendaItem.Event {
        return agendaDao.getEventById(id).toEventAgendaItem()
    }

    override suspend fun insertEvent(event: AgendaItem.Event) {
        agendaDao.insertEvent(event.toEventEntity())
    }

    override suspend fun deleteEventById(id: String) {
        agendaDao.deleteEventById(id)
    }

    override suspend fun getReminderById(id: String): AgendaItem.Reminder {
        return agendaDao.getReminderById(id).toReminderAgendaItem()
    }

    override suspend fun insertReminder(reminder: AgendaItem.Reminder) {
        agendaDao.insertReminder(reminder.toReminderEntity())
    }

    override suspend fun deleteReminderById(id: String) {
        agendaDao.deleteReminderById(id)
    }

    override suspend fun getTaskById(id: String): AgendaItem.Task {
        return agendaDao.getTaskById(id).toTaskAgendaItem()
    }

    override suspend fun insertTask(task: AgendaItem.Task) {
        agendaDao.insertTask(task.toTaskEntity())
    }

    override suspend fun deleteTaskById(id: String) {
        agendaDao.deleteTaskById(id)
    }
}