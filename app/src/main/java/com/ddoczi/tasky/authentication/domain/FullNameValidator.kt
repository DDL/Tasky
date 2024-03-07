package com.ddoczi.tasky.authentication.domain

class FullNameValidator {
    operator fun invoke(fullName: String): Boolean {
        return fullName.length in 4..50
    }
}