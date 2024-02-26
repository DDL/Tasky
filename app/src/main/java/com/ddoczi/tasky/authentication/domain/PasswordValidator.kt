package com.ddoczi.tasky.authentication.domain

class PasswordValidator {
    fun isPasswordValid(password: String): Boolean {
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasValidLength = password.length >= 9

        return hasLowerCase && hasUpperCase && hasDigit && hasValidLength
    }
}