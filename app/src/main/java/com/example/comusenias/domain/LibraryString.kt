package com.example.comusenias.domain

object LibraryString {

    const val regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    const val regexPassword= "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"


    fun validEmail(email: String): Boolean {
        return email.matches(regexEmail.toRegex())
    }

    fun validPassword(password: String): Boolean {
        return password.matches(regexPassword.toRegex())
    }

    fun validUserName(userName: String): Boolean {
        return userName.length >= 3
    }
}