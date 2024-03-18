package com.ddoczi.tasky.agenda.di

import android.app.Application
import androidx.room.Room
import com.ddoczi.tasky.agenda.data.local.AgendaDao
import com.ddoczi.tasky.agenda.data.local.AgendaDatabase
import com.ddoczi.tasky.agenda.data.repository.EventRepositoryImpl
import com.ddoczi.tasky.agenda.data.repository.ReminderRepositoryImpl
import com.ddoczi.tasky.agenda.data.repository.TaskRepositoryImpl
import com.ddoczi.tasky.agenda.domain.repository.EventRepository
import com.ddoczi.tasky.agenda.domain.repository.ReminderRepository
import com.ddoczi.tasky.agenda.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgendaModule {
    @Provides
    @Singleton
    fun provideAgendaDatabase(application: Application) : AgendaDatabase {
        return Room
            .databaseBuilder(
                application,
                AgendaDatabase::class.java,
                "agenda_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideAgendaDao(agendaDatabase: AgendaDatabase) : AgendaDao {
        return agendaDatabase.agendaDao()
    }

    @Singleton
    @Provides
    fun provideEventRepository(agendaDao: AgendaDao): EventRepository {
        return EventRepositoryImpl(agendaDao)
    }

    @Singleton
    @Provides
    fun provideTaskRepository(agendaDao: AgendaDao): TaskRepository {
        return TaskRepositoryImpl(agendaDao)
    }

    @Singleton
    @Provides
    fun provideReminderRepository(agendaDao: AgendaDao): ReminderRepository {
        return ReminderRepositoryImpl(agendaDao)
    }
}