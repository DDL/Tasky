package com.ddoczi.tasky.core.domain.reposotory

interface AuthenticationRepository {
    suspend fun register(fullName: String, email: String, password: String) : Result<Unit>
}