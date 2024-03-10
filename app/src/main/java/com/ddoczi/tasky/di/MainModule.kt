package com.ddoczi.tasky.di

import com.ddoczi.tasky.util.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideCoroutineDispatcher() = DispatcherProviderImpl
}
