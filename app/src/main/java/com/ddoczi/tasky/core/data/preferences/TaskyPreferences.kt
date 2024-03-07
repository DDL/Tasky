package com.ddoczi.tasky.core.data.preferences

import android.content.SharedPreferences
import com.ddoczi.tasky.core.domain.model.LoggedInUser
import com.ddoczi.tasky.core.domain.preferences.Preferences

class TaskyPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(Preferences.TOKEN_KEY, token).apply()
    }

    override fun saveFullName(fullName: String) {
        sharedPreferences.edit().putString(Preferences.FULL_NAME_KEY, fullName).apply()
    }

    override fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(Preferences.USER_ID_KEY, userId).apply()
    }

    override fun loadLoggedInUser(): LoggedInUser? {
        val token = sharedPreferences.getString(Preferences.TOKEN_KEY, "") ?: ""
        val fullName = sharedPreferences.getString(Preferences.FULL_NAME_KEY, "") ?: ""
        val userId = sharedPreferences.getString(Preferences.USER_ID_KEY, "") ?: ""
        if (token.isBlank() || fullName.isBlank() || userId.isBlank()) {
            return null
        }

        return LoggedInUser(
            token = token,
            fullName = fullName,
            userId = userId
        )
    }

    override fun deleteUser() {
        sharedPreferences.edit()
            .remove(Preferences.TOKEN_KEY)
            .remove(Preferences.FULL_NAME_KEY)
            .remove(Preferences.USER_ID_KEY)
            .apply()
    }
}