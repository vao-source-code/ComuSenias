package com.example.comusenias.domain.library

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Date

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

    fun decodeURL(encodedUrl: String): String {
        return try {
            URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
        } catch (e: UnsupportedEncodingException) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace()
            ""
        }
    }

    fun validPhone(phone: String) = phone.matches(regexPhone.toRegex())

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

}