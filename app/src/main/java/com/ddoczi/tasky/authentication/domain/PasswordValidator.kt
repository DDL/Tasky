package com.ddoczi.tasky.authentication.domain

class PasswordValidator {
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 9 &&
                password.contains(Regex(".*[A-Z].*")) &&
                password.contains(Regex(".*[a-z].*")) &&
                password.contains(Regex(".*\\d.*"))
    }
}