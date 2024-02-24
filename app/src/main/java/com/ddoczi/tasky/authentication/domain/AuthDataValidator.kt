package com.ddoczi.tasky.authentication.domain

data class AuthDataValidator(
    val fullNameIsValid: String,
    val emailIsValid: String,
    val passwordIsValid: String
)
