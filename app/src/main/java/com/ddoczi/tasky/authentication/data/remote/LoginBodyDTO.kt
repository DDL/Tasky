package com.ddoczi.tasky.authentication.data.remote

import com.squareup.moshi.Json

data class LoginBodyDTO(
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "password")
    val password: String
)