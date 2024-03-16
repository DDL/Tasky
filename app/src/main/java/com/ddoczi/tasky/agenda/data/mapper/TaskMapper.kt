package com.ddoczi.tasky.agenda.data.mapper

import com.ddoczi.tasky.agenda.data.local.entity.TaskEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.core.util.toLocalDateTime
import com.ddoczi.tasky.core.util.toLong

fun TaskEntity.toTaskAgendaItem(): AgendaItem.Task {
    return AgendaItem.Task(
        taskId = this.taskId,
        taskTitle = this.title,
        taskDescription = this.description,
        taskDate = this.dueDateTime.toLocalDateTime(),
        taskRemindAt = this.remindAt.toLocalDateTime(),
        isDone = this.isDone
    )
}

fun AgendaItem.Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        taskId = this.taskId,
        title = this.taskTitle,
        description = this.taskDescription,
        remindAt = this.taskRemindAt.toLong(),
        dueDateTime = this.taskDate.toLong(),
        isDone = this.isDone
    )
}