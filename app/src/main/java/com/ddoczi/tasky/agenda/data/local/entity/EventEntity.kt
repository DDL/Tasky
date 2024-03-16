package com.ddoczi.tasky.agenda.data.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["eventId"])
data class EventEntity(
    val eventId: String,
    val title: String,
    val description: String,
    val remindAt: Long,
    val fromDateTime: Long,
    val toDateTime: Long,
)