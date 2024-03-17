package com.ddoczi.tasky.agenda.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ddoczi.tasky.agenda.data.local.entity.EventEntity
import com.ddoczi.tasky.agenda.data.local.entity.ReminderEntity
import com.ddoczi.tasky.agenda.data.local.entity.TaskEntity

@Dao
interface AgendaDao {
    @Query("SELECT * FROM EventEntity WHERE eventId = :id")
    suspend fun getEventById(id: String): EventEntity

    @Query("DELETE FROM EventEntity WHERE eventId = :id")
    suspend fun deleteEventById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Query("SELECT * FROM ReminderEntity WHERE reminderId = :id")
    suspend fun getReminderById(id: String): ReminderEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity)

    @Query("DELETE FROM ReminderEntity WHERE reminderId = :id")
    suspend fun deleteReminderById(id: String)

    @Query("SELECT * FROM TaskEntity WHERE taskId = :id")
    suspend fun getTaskById(id: String): TaskEntity

    @Query("DELETE FROM TaskEntity WHERE taskId = :id")
    suspend fun deleteTaskById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)
}