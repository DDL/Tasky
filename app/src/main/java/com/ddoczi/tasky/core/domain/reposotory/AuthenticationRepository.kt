package com.ddoczi.tasky.core.domain.reposotory

import com.ddoczi.tasky.core.domain.model.LoggedInUser

interface AuthenticationRepository {
    suspend fun register(fullName: String, email: String, password: String) : Result<Unit>
    suspend fun authenticate() : Result<Unit>
    suspend fun login(email: String, password: String) : Result<LoggedInUser>
}