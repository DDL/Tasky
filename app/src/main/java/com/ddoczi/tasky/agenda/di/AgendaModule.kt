package com.ddoczi.tasky.agenda.di

import android.app.Application
import androidx.room.Room
import com.ddoczi.tasky.agenda.data.AgendaRepositoryImpl
import com.ddoczi.tasky.agenda.data.local.AgendaDao
import com.ddoczi.tasky.agenda.data.local.AgendaDatabase
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

    @Provides
    @Singleton
    fun provideAgendaRepository(agendaDao: AgendaDao) :AgendaRepositoryImpl {
        return AgendaRepositoryImpl(agendaDao)
    }
}