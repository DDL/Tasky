package com.ddoczi.tasky.authentication.presentation.login

import androidx.lifecycle.ViewModel
import com.ddoczi.tasky.authentication.domain.AuthDataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authDataValidator: AuthDataValidator
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    isEmailValid = authDataValidator.validEmail(event.email),
                    isEmailError = false
                )
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    isPasswordError = false
                )
            }
            is LoginEvent.OnPasswordVisibilityToggle -> {
                _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }
            is LoginEvent.Login -> {
                if (!authDataValidator.validEmail(_state.value.email)) {
                    _state.value = _state.value.copy(isEmailError = true)
                }
                if (!authDataValidator.validPassword(_state.value.password)) {
                    _state.value = _state.value.copy(isPasswordError = true)
                }
                if (!_state.value.isEmailError && !_state.value.isPasswordError) {
                    submit(_state.value.email, _state.value.password)
                }
            }
        }
    }

    private fun submit(email: String, password: String) {
        // TODO
    }
}