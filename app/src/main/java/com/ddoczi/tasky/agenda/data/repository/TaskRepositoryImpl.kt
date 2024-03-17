package com.ddoczi.tasky.agenda.data.repository

import com.ddoczi.tasky.agenda.data.local.AgendaDao
import com.ddoczi.tasky.agenda.data.mapper.toTaskAgendaItem
import com.ddoczi.tasky.agenda.data.mapper.toTaskEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.TaskRepository

class TaskRepositoryImpl(private val agendaDao: AgendaDao) : TaskRepository {
    override suspend fun getTaskById(id: String): AgendaItem.Task {
        return agendaDao.getTaskById(id).toTaskAgendaItem()
    }
    override suspend fun insertTask(task: AgendaItem.Task) {
        agendaDao.insertTask(task.toTaskEntity())
    }
    override suspend fun deleteTaskById(id: String) {
        agendaDao.deleteTaskById(id)
    }
    override suspend fun changeTaskStatus(id: String, isDone: Boolean) {
        val task = getTaskById(id).copy(isDone = isDone)
        insertTask(task)
    }
}