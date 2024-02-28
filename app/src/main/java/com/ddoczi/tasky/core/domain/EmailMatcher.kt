package com.ddoczi.tasky.core.domain

interface EmailMatcher {
    fun matches(email: String): Boolean
}