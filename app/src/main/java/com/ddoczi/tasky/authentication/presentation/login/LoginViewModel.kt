package com.ddoczi.tasky.authentication.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ddoczi.tasky.authentication.domain.EmailValidator
import com.ddoczi.tasky.authentication.domain.PasswordValidator

// TODO add DI and update validation
class LoginViewModel(): ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                state = state.copy(
                    email = event.email,
                    isEmailValid = EmailValidator().isEmailValid(event.email),
                    isEmailError = false
                )
            }
            is LoginEvent.OnPasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    isPasswordValid = PasswordValidator().isPasswordValid(event.password),
                    isPasswordError = false
                )
            }
            is LoginEvent.OnPasswordVisibilityToggle -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            is LoginEvent.Login -> {
                if (!EmailValidator().isEmailValid(state.email)) {
                    state = state.copy(isEmailError = true)
                }
                if (!PasswordValidator().isPasswordValid(state.password)) {
                    state = state.copy(isPasswordError = true)
                }
                if (!state.isEmailError && !state.isPasswordError) {
                    submit(state.email, state.password)
                }
            }
        }
    }

    private fun submit(email: String, password: String) {
        // TODO
    }
}