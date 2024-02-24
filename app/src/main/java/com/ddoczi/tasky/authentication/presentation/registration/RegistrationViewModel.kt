package com.ddoczi.tasky.authentication.presentation.registration

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ddoczi.tasky.authentication.domain.EmailValidator
import com.ddoczi.tasky.authentication.domain.FullNameValidator
import com.ddoczi.tasky.authentication.domain.PasswordValidator

// TODO add DI and update validation
class RegistrationViewModel: ViewModel() {
    var state by mutableStateOf(RegistrationState())
        private set

    fun onEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.OnFullNameChanged -> {
                state = state.copy(
                    fullName = event.fullName,
                    isFullNameValid = FullNameValidator().isFullNameIsValid(event.fullName),
                    fullNameError = false,
                )
            }
            is RegistrationEvent.OnEmailChanged -> {
                state = state.copy(
                    email = event.email,
                    isEmailValid = EmailValidator().isEmailValid(event.email),
                    emailError = false,
                )
            }
            is RegistrationEvent.OnPasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    isPasswordValid = PasswordValidator().isPasswordValid(event.password),
                    passwordError = false,
                )
            }
            is RegistrationEvent.OnPasswordVisibilityToggle -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            is RegistrationEvent.Register -> {
                if(!FullNameValidator().isFullNameIsValid(state.fullName)) {
                    state = state.copy(fullNameError = true)
                }
                if(!EmailValidator().isEmailValid(state.email)) {
                    state = state.copy(emailError = true)
                }
                if(!PasswordValidator().isPasswordValid(state.password)) {
                    state = state.copy(passwordError = true)
                }
                if(!state.fullNameError && !state.emailError && !state.passwordError) {
                    submit(state.fullName, state.email, state.password)
                }
            }
        }
    }
    private fun submit(fullName: String, email: String, password: String) {
        // TODO
    }
}