package com.example.comusenias.data.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.model.UserModel
import com.example.comusenias.domain.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {


    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(user.user!!)
        } catch (e: Exception) {
            //Implementar un log
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override suspend fun register(user: UserModel): Response<FirebaseUser> {
        return try {
            val user = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                .await()
            Response.Success(user.user!!)
        } catch (e: Exception) {
            //Implementar un log
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}