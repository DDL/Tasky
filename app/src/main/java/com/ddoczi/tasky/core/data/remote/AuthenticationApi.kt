package com.ddoczi.tasky.core.data.remote

import com.ddoczi.tasky.authentication.data.remote.RegistrationBodyDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    companion object {
        const val BASE_URL = "https://tasky.pl-coding.com/"
    }

    @POST("register")
    suspend fun register(@Body registrationBodyDTO: RegistrationBodyDTO)
}