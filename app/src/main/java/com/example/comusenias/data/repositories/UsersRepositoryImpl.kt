package com.example.comusenias.data.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.repositories.UsersRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val userRef: CollectionReference) : UsersRepository {


    override suspend fun createUser(user: User): Response<Boolean> {
       return try {
            userRef.document(user.id).set(user).await()
            Response.Success(true)
       }catch (e : Exception){
           e.printStackTrace()
           Response.Error(e)
       }
    }
}