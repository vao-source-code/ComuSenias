package com.example.comusenias.data.repositories

import android.util.Log
import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.model.game.LevelModel
import com.example.comusenias.domain.models.model.game.SubLevelModel
import com.example.comusenias.domain.repositories.LevelRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class LevelRepositoryImpl @Inject constructor(
    @Named(FirebaseConstants.LEVEL_COLLECTION) private val levelRef: CollectionReference,
) : LevelRepository {


    override fun getLevelById(id: String): Flow<LevelModel> = callbackFlow {
        val snapshotListener = levelRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(LevelModel::class.java) ?: LevelModel("", "")
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getLevels(): Flow<Response<List<LevelModel>>> = callbackFlow {
        val snapshotListener = levelRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {
                val postsResponse = if (snapshot != null) {
                    var posts = snapshot.toObjects(LevelModel::class.java)

                    snapshot.documents.forEachIndexed { index, document ->
                        posts[index].id = document.id
                    }

                    Log.d("TAG", "getLevels: ${posts.size}" + posts.toString())
                    val idUserArray = ArrayList<String>()


                    val idUserList = idUserArray.toSet().toList() // ELEMENTOS SIN REPETIR

                    Response.Success(posts)
                } else {
                    Response.Error(e)
                }
                trySend(postsResponse)
            }

        }
        awaitClose {
            snapshotListener.remove()
        }

    }

    override fun getWhereSubLevels(idLevel: String): Flow<List<SubLevelModel>> {
        TODO("Not yet implemented")
    }
}