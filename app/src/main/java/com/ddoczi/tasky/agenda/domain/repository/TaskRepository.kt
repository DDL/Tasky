package com.ddoczi.tasky.agenda.domain.repository

import com.ddoczi.tasky.agenda.domain.model.AgendaItem

interface TaskRepository {
    suspend fun getTaskById(id: String): AgendaItem.Task
    suspend fun insertTask(task: AgendaItem.Task)
    suspend fun deleteTaskById(id: String)
    suspend fun changeTaskStatus(id: String, isDone: Boolean)
}