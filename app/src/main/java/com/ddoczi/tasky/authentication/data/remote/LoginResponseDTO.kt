package com.ddoczi.tasky.authentication.data.remote

import com.squareup.moshi.Json

data class LoginResponseDTO(
    @field:Json(name = "token")
    val token: String,
    @field:Json(name = "userId")
    val userId: String,
    @field:Json(name = "fullName")
    val fullName: String
)