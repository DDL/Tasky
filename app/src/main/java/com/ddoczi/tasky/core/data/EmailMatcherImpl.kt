package com.ddoczi.tasky.core.data

import android.util.Patterns
import com.ddoczi.tasky.core.domain.EmailMatcher

class EmailMatcherImpl: EmailMatcher {
    override fun matches(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}