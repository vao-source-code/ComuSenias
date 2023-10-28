package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.UserModel
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun register(user: UserModel): Response<FirebaseUser>
    fun logout()
}