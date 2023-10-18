package com.example.comusenias.domain.library

import android.os.Build
import androidx.annotation.RequiresApi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.time.format.DateTimeParseException

object LibraryString {
    private const val regexEmail =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    private const val regexPassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"
    private const val regexPhone = "^[0-9]{10,15}$"  // Regex for validating phone numbers
    private const val dateFormat = "yyyy-MM-dd"  // Format for date of birth validation

    fun validEmail(email: String) = email.matches(regexEmail.toRegex())
    fun validPassword(password: String) = password.matches(regexPassword.toRegex())
    fun validUserName(userName: String) = userName.length >= 3
    fun encodeURL(image: String): String =
        URLEncoder.encode(image, StandardCharsets.UTF_8.toString())

    fun validPhone(phone: String) = phone.matches(regexPhone.toRegex())

    @RequiresApi(Build.VERSION_CODES.O)
    fun validDateOfBirth(dateOfBirth: String): Boolean {
        return try {
            val parsedDate = LocalDate.parse(dateOfBirth)
            val currentDate = LocalDate.now()
            !parsedDate.isAfter(currentDate)
        } catch (e: DateTimeParseException) {
            false
        }
    }

}