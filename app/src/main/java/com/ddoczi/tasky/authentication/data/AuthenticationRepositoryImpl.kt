package com.ddoczi.tasky.authentication.data

import com.ddoczi.tasky.authentication.data.remote.RegistrationBodyDTO
import com.ddoczi.tasky.core.data.remote.AuthenticationApi
import com.ddoczi.tasky.core.domain.reposotory.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authenticationApi: AuthenticationApi
) : AuthenticationRepository {
    override suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val body = RegistrationBodyDTO(
                fullName = fullName,
                email = email,
                password = password
            )
            authenticationApi.register(body)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
