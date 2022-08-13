package com.hashconcepts.composeinstagramclone.common.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hashconcepts.composeinstagramclone.auth.domain.model.User
import com.hashconcepts.composeinstagramclone.common.utils.Constants
import javax.inject.Inject

/**
 * @created 29/07/2022 - 10:44 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
class SharedPrefUtil @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveCurrentUser(user: User?) {
        val gson = Gson().toJson(user)
        sharedPreferences.edit().putString(Constants.CURRENT_USER, gson).apply()
    }

    fun getCurrentUser(): User {
        val json = sharedPreferences.getString(Constants.CURRENT_USER, null)
        val type = object : TypeToken<User>() {}.type
        return Gson().fromJson(json, type)
    }

    fun saveString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? = sharedPreferences.getString(key, null);
}