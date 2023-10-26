package com.example.comusenias.presentation.screen.gallery

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("my_preference", MODE_PRIVATE)
    }
    fun saveString(key: String, value: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return getSharedPreferences().getString(key, defaultValue) ?: defaultValue
    }

    fun removeKey(key: String) {
        val editor = getSharedPreferences().edit()
        editor.remove(key)
        editor.apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = getSharedPreferences().edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getSharedPreferences().getBoolean(key, defaultValue)
    }
}







