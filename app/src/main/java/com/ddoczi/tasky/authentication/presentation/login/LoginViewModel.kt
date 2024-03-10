package com.ddoczi.tasky.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddoczi.tasky.authentication.domain.AuthDataValidator
import com.ddoczi.tasky.core.domain.preferences.Preferences
import com.ddoczi.tasky.core.domain.reposotory.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val authDataValidator: AuthDataValidator,
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        isEmailValid = authDataValidator.validEmail(event.email),
                        isEmailError = false
                    )
                }
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        isPasswordValid = authDataValidator.validPassword(event.password),
                        isPasswordError = false
                    )
                }
            }
            is LoginEvent.OnPasswordVisibilityToggle -> {
                _state.update {
                    it.copy(isPasswordVisible = !it.isPasswordVisible)
                }
            }
            is LoginEvent.Login -> {
                if (!authDataValidator.validEmail(_state.value.email)) {
                    _state.update {
                        it.copy(isEmailError = true)
                    }
                }
                if (!authDataValidator.validPassword(_state.value.password)) {
                    _state.update {
                        it.copy(isPasswordError = true)
                    }
                }
                if (!_state.value.isEmailError && !_state.value.isPasswordError) {
                    submit(_state.value.email, _state.value.password)
                }
            }
            else -> { Unit }
        }
    }

    private fun submit(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password)
                .onSuccess {
                    println(it)
                    preferences.saveLoggedInUser(it)
                    _state.update { state ->
                        state.copy(isLoggedIn = true)
                    }
                }
                .onFailure {
                    println(it)
                }
        }
    }
}