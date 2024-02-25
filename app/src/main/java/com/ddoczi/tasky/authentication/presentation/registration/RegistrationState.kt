package com.ddoczi.tasky.authentication.presentation.registration

data class RegistrationState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val isFullNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val fullNameError: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val isPasswordVisible: Boolean = false
)
