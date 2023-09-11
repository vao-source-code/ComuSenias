package com.example.comusenias.data.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.repositories.UsersRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snap  = userRef.document(id).addSnapshotListener{ it , e ->

            val user = it?.toObject(User::class.java) ?: User() // todo deberia ver si puedo guardar los datos y si no hay internet acceder a ello
            trySend(user)
        }

        awaitClose { snap.remove() }
    }

    override suspend fun updateUser(user: User): Response<Boolean> {
        return try {
            val mapImage : MutableMap<String , Any > = HashMap()
            mapImage["userName"] = user.userName
            mapImage["image"] = user.image
            userRef.document(user.id).update(mapImage).await()
            Response.Success(true)
        }catch (e : Exception){
            e.printStackTrace()
            Response.Error(e)
        }
    }
}