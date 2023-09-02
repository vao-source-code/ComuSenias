package com.example.comusenias.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun register(user: User): Response<FirebaseUser>
    fun logout()
}