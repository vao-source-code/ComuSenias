package com.example.comusenias.domain.library

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object LibraryDate {
    fun validDateOfBirth(dateOfBirth: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return try {
            val dateOfBirthAsDate = dateFormat.parse(dateOfBirth)
            val currentDate = Date()
            dateOfBirthAsDate.before(currentDate)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun validateRegistrationExpirationDate(expirationDate: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return try {
            val expirationDateAsDate = dateFormat.parse(expirationDate)
            val currentDate = Date()
            expirationDateAsDate.after(currentDate)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun validateAgeRequirementForRegistration(expirationDate: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return try {
            val expirationDateAsDate = dateFormat.parse(expirationDate)
            val currentDate = Date()

            val calendar = Calendar.getInstance()
            calendar.time = currentDate
            calendar.add(Calendar.YEAR, -18)
            expirationDateAsDate.after(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getCurrentDateTimeAsString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }
}