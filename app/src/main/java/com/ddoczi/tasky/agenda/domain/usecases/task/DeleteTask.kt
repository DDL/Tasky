package com.ddoczi.tasky.agenda.domain.usecases.task

import com.ddoczi.tasky.agenda.domain.repository.AgendaRepository

class DeleteTask(private val repository: AgendaRepository) {
    suspend operator fun invoke(id: String) { repository.deleteTaskById(id) }
}