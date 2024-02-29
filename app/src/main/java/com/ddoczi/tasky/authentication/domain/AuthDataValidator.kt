package com.ddoczi.tasky.authentication.domain

import com.ddoczi.tasky.core.domain.EmailValidator

data class AuthDataValidator(
    val validFullName: FullNameValidator,
    val validEmail: EmailValidator,
    val validPassword: PasswordValidator
)
