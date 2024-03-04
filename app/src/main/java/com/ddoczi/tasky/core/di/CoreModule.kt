package com.ddoczi.tasky.core.di

import com.ddoczi.tasky.authentication.data.AuthenticationRepositoryImpl
import com.ddoczi.tasky.core.data.EmailMatcherImpl
import com.ddoczi.tasky.core.data.remote.ApiKeyInterceptor
import com.ddoczi.tasky.core.data.remote.AuthenticationApi
import com.ddoczi.tasky.core.domain.EmailMatcher
import com.ddoczi.tasky.core.domain.EmailValidator
import com.ddoczi.tasky.core.domain.reposotory.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskyApi(okHttpClient: OkHttpClient): AuthenticationApi {
        return Retrofit.Builder()
            .baseUrl(AuthenticationApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: AuthenticationApi): AuthenticationRepository {
        return AuthenticationRepositoryImpl(api)
    }

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