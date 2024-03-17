package com.ddoczi.tasky.agenda.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ddoczi.tasky.agenda.data.local.entity.EventEntity
import com.ddoczi.tasky.agenda.data.local.entity.ReminderEntity
import com.ddoczi.tasky.agenda.data.local.entity.TaskEntity

@Database(
    entities = [
        EventEntity::class,
        ReminderEntity::class,
        TaskEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AgendaDatabase : RoomDatabase() {
    abstract fun agendaDao(): AgendaDao
}