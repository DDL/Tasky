package com.ddoczi.tasky.agenda.domain.usecases.event

import com.ddoczi.tasky.agenda.domain.repository.AgendaRepository

class DeleteEvent(private val repository: AgendaRepository) {
    suspend operator fun invoke(id: String) { repository.deleteEventById(id) }
}