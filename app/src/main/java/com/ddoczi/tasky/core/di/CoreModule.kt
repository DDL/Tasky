package com.ddoczi.tasky.core.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.ddoczi.tasky.authentication.data.AuthenticationRepositoryImpl
import com.ddoczi.tasky.core.data.EmailMatcherImpl
import com.ddoczi.tasky.core.data.preferences.TaskyPreferences
import com.ddoczi.tasky.core.data.remote.interceptors.ApiKeyInterceptor
import com.ddoczi.tasky.core.data.remote.AuthenticationApi
import com.ddoczi.tasky.core.data.remote.interceptors.JwtInterceptor
import com.ddoczi.tasky.core.domain.EmailMatcher
import com.ddoczi.tasky.core.domain.EmailValidator
import com.ddoczi.tasky.core.domain.preferences.Preferences
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
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return TaskyPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(app: Application): SharedPreferences {
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "encrypted_shared_prefs",
            masterKey,
            app.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(preferences: Preferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(JwtInterceptor(preferences))
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