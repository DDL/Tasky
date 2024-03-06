package com.ddoczi.tasky.core.domain.model

data class LoggedInUser(
    val token: String,
    val userId: String,
    val fullName: String
)
