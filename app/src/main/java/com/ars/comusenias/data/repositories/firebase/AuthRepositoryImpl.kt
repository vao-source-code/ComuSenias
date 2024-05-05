package com.ars.comusenias.data.repositories.firebase

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
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
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
            Response.ErrorFirebase(e.errorCode)
        }
    }

    override suspend fun register(user: UserModel): Response<FirebaseUser> {
        return try {
            val user = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                .await()
            Response.Success(user.user!!)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
            Response.ErrorFirebase(e.errorCode)
        }
    }

    override suspend fun resetPassword(email: String): Response<Boolean> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Response.Success(true)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
            Response.ErrorFirebase(e.errorCode)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}