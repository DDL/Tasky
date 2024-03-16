package com.ddoczi.tasky.agenda.data.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["reminderId"])
data class ReminderEntity(
    val reminderId: String,
    val title: String,
    val description: String,
    val remindAt: Long,
    val reminderDateTime: Long
)