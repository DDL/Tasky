package com.ddoczi.tasky.core.data.preferences

import android.content.SharedPreferences
import com.ddoczi.tasky.core.domain.model.LoggedInUser
import com.ddoczi.tasky.core.domain.preferences.Preferences

class TaskyPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveLoggedInUser(loggedInUser: LoggedInUser) {
        sharedPreferences.edit()
            .putString(Preferences.TOKEN_KEY, loggedInUser.token)
            .putString(Preferences.FULL_NAME_KEY, loggedInUser.fullName)
            .putString(Preferences.USER_ID_KEY, loggedInUser.userId)
            .apply()
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