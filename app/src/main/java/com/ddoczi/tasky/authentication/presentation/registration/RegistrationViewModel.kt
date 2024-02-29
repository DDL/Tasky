package com.ddoczi.tasky.authentication.presentation.registration

import androidx.lifecycle.ViewModel
import com.ddoczi.tasky.authentication.domain.AuthDataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authDataValidator: AuthDataValidator
): ViewModel() {
    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.OnFullNameChanged -> {
                _state.value = _state.value.copy(
                    fullName = event.fullName,
                    isFullNameValid = authDataValidator.validFullName(event.fullName),
                    fullNameError = false,
                )
            }
            is RegistrationEvent.OnEmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    isEmailValid = authDataValidator.validEmail(event.email),
                    emailError = false,
                )
            }
            is RegistrationEvent.OnPasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    isPasswordValid = authDataValidator.validPassword(event.password),
                    passwordError = false,
                )
            }
            is RegistrationEvent.OnPasswordVisibilityToggle -> {
                _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }
            is RegistrationEvent.Register -> {
                if(!authDataValidator.validFullName(_state.value.fullName)) {
                    _state.value = _state.value.copy(fullNameError = true)
                }
                if(!authDataValidator.validEmail(_state.value.email)) {
                    _state.value = _state.value.copy(emailError = true)
                }
                if(!authDataValidator.validPassword(_state.value.password)) {
                    _state.value = _state.value.copy(passwordError = true)
                }
                if(!_state.value.fullNameError && !_state.value.emailError && !_state.value.passwordError) {
                    submit(_state.value.fullName, _state.value.email, _state.value.password)
                }
            }
        }
    }
    private fun submit(fullName: String, email: String, password: String) {
        // TODO
    }
}