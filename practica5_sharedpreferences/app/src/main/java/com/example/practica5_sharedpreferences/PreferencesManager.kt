package com.example.practica5_sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context
                            .getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    companion object {
        const val KEY_USERNAME = "key_username"
        const val KEY_NOTIFICATIONS = "key_notifications"
        const val KEY_DARK_THEME = "key_dark_theme"
    }

    fun saveSettings(username: String, notifications: Boolean, darkTheme: Boolean) {
        sharedPreferences.edit {
            putString(KEY_USERNAME, username)
            putBoolean(KEY_NOTIFICATIONS, notifications)
            putBoolean(KEY_DARK_THEME, darkTheme)
        }
    }

    fun getUsername(): String {
        return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    }

    fun getNotifications(): Boolean {
        return sharedPreferences.getBoolean(KEY_NOTIFICATIONS, false)
    }

    fun getDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_DARK_THEME, false)
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }

}