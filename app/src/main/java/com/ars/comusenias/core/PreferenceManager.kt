package com.ars.comusenias.core

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

//TODO: 1. Revisar esta clase
class PreferenceManager(private val context: Context) {

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("my_preference", MODE_PRIVATE)
    }

    fun saveString(key: String, value: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveInt(key: String , value: Int){
        val editor = getSharedPreferences().edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun clearInt(key: String){
        val editor = getSharedPreferences().edit()
        editor.remove(key)
        editor.apply()
    }
    fun getString(key: String, defaultValue: String): String {
        return getSharedPreferences().getString(key, defaultValue) ?: defaultValue
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return getSharedPreferences().getInt(key, defaultValue) ?: defaultValue
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