package com.ddoczi.tasky.agenda.data.repository

import com.ddoczi.tasky.agenda.data.local.AgendaDao
import com.ddoczi.tasky.agenda.data.mapper.toEventAgendaItem
import com.ddoczi.tasky.agenda.data.mapper.toEventEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.EventRepository

class EventRepositoryImpl(private val agendaDao: AgendaDao) : EventRepository {
    override suspend fun getEventById(id: String): AgendaItem.Event {
        return agendaDao.getEventById(id).toEventAgendaItem()
    }

    override suspend fun insertEvent(event: AgendaItem.Event) {
        agendaDao.insertEvent(event.toEventEntity())
    }

    override suspend fun deleteEventById(id: String) {
        agendaDao.deleteEventById(id)
    }
}