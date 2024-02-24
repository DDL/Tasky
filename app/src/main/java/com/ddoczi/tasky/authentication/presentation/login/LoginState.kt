package com.ddoczi.tasky.authentication.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val isPasswordVisible: Boolean = false
)
