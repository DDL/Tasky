package com.ddoczi.tasky.authentication.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isPasswordVisible: Boolean = false
)
