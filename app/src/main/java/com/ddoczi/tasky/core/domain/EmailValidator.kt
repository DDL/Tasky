package com.ddoczi.tasky.core.domain

class EmailValidator(
    private val emailMatcher: EmailMatcher
) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.matches(email)
    }
}