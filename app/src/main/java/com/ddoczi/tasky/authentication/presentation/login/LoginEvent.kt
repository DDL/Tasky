package com.ddoczi.tasky.authentication.presentation.login

sealed class LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    data object OnPasswordVisibilityToggle : LoginEvent()
    data object Login : LoginEvent()
    data object OnSignUpClick : LoginEvent()
}