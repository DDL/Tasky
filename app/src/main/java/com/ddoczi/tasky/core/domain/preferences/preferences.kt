package com.ddoczi.tasky.core.domain.preferences

import com.ddoczi.tasky.core.domain.model.LoggedInUser

interface Preferences {
    fun saveToken(token: String)
    fun saveFullName(fullName: String)
    fun saveUserId(userId: String)
    fun loadLoggedInUser() : LoggedInUser?
    fun deleteUser()

    companion object {
        const val TOKEN_KEY = "token"
        const val FULL_NAME_KEY = "full_name"
        const val USER_ID_KEY = "user_id"
    }
}