package com.ddoczi.tasky.core.di

import com.ddoczi.tasky.core.data.EmailMatcherImpl
import com.ddoczi.tasky.core.domain.EmailMatcher
import com.ddoczi.tasky.core.domain.EmailValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideEmailValidator(emailMatcher: EmailMatcher): EmailValidator {
        return EmailValidator(emailMatcher)
    }
}