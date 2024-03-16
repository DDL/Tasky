package com.ddoczi.tasky.agenda.domain.usecases.reminder

import com.ddoczi.tasky.agenda.domain.repository.AgendaRepository

class DeleteReminder(private val repository: AgendaRepository
) {
    suspend operator fun invoke(id: String) { repository.deleteReminderById(id) }
}