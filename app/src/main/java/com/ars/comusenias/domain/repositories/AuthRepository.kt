package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.UserModel
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun register(user: UserModel): Response<FirebaseUser>

    suspend fun resetPassword(email: String): Response<Boolean>
    fun logout()
}