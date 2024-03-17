package com.ddoczi.tasky.agenda.domain.repository

import com.ddoczi.tasky.agenda.domain.model.AgendaItem

interface EventRepository {
    suspend fun getEventById(id: String): AgendaItem.Event
    suspend fun insertEvent(event: AgendaItem.Event)
    suspend fun deleteEventById(id: String)
}