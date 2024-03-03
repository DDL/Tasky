package com.ddoczi.tasky.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import com.ddoczi.tasky.BuildConfig

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("x-api-key", BuildConfig.API_KEY).build()
        return chain.proceed(request)
    }
}