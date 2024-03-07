package com.ddoczi.tasky.authentication.presentation.registration

sealed class RegistrationEvent {
    data class OnFullNameChanged(val fullName: String) : RegistrationEvent()
    data class OnEmailChanged(val email: String) : RegistrationEvent()
    data class OnPasswordChanged(val password: String) : RegistrationEvent()
    data object OnPasswordVisibilityToggle : RegistrationEvent()
    data object Submit : RegistrationEvent()
    data object OnNavigateBack : RegistrationEvent()
}