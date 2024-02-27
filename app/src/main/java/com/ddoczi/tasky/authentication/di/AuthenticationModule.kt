package com.ddoczi.tasky.authentication.di

import com.ddoczi.tasky.authentication.domain.AuthDataValidator
import com.ddoczi.tasky.authentication.domain.EmailValidator
import com.ddoczi.tasky.authentication.domain.FullNameValidator
import com.ddoczi.tasky.authentication.domain.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthDataValidator(validEmail: EmailValidator): AuthDataValidator {
        return AuthDataValidator(
            FullNameValidator(),
            validEmail,
            PasswordValidator()
        )
    }
}