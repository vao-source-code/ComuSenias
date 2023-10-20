package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.users.UserModel
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun register(user: UserModel): Response<FirebaseUser>

    suspend fun resetPassword(email: String): Response<Boolean>
    fun logout()
}