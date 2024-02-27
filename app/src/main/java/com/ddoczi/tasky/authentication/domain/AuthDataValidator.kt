package com.ddoczi.tasky.authentication.domain

data class AuthDataValidator(
    val validFullName: FullNameValidator,
    val validEmail: EmailValidator,
    val validPassword: PasswordValidator
)
