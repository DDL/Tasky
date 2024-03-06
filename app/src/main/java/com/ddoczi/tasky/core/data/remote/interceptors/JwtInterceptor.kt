package com.ddoczi.tasky.core.data.remote.interceptors

import com.ddoczi.tasky.core.domain.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(
    private val preferences: Preferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.loadLoggedInUser()?.token
        val request = chain.request().newBuilder()

        token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}