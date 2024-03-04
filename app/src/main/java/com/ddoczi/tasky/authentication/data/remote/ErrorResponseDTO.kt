package com.ddoczi.tasky.authentication.data.remote

import com.squareup.moshi.Json

data class ErrorResponseDTO(
    @field:Json(name = "message")
    val message: String
)