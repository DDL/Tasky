package com.ddoczi.tasky.agenda.data.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["taskId"])
data class TaskEntity(
    val taskId: String,
    val title: String,
    val description: String,
    val remindAt: Long,
    val dueDateTime: Long,
    val isDone: Boolean
)