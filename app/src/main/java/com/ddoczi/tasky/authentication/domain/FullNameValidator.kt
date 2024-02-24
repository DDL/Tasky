package com.ddoczi.tasky.authentication.domain

class FullNameValidator {
    fun isFullNameIsValid(fullName: String): Boolean {
        return fullName.length in 4..50
    }
}