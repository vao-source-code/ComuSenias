package com.example.comusenias.data.repositories

import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.Level
import com.example.comusenias.domain.models.game.SubLevel
import com.example.comusenias.domain.repositories.LevelRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class LevelRepositoryImpl @Inject constructor(
    @Named(FirebaseConstants.LEVEL_COLLECTION) private val levelRef: CollectionReference,
    @Named(FirebaseConstants.SUB_LEVEL_COLLECTION) private val subLevelRef: CollectionReference,
) : LevelRepository {

    override fun searchLevelById(id: String): Flow<Level> = callbackFlow {
        val snapshotListener = levelRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(Level::class.java) ?: Level()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getLevels(): Flow<Response<List<Level>>> = callbackFlow {
        val snapshotListener = levelRef.addSnapshotListener { snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val levelResponse = if (snapshot != null) {
                    val level = snapshot.toObjects(Level::class.java)
                    snapshot.documents.forEachIndexed { index, document ->
                        level[index].id = document.id
                    }
                    val idLevelModelArray = ArrayList<String>()
                    level.forEach { l ->
                        l.subLevel.forEach { id ->
                            idLevelModelArray.add(id)
                        }
                    }
                    level.forEach { l ->
                        subLevelRef.whereEqualTo(SubLevel.ID_LEVEL, l.id).get().await()
                            .forEach { document ->
                                l.subLevelModel.add(document.toObject(SubLevel::class.java))
                            }
                    }
                    Response.Success(level)
                } else {
                    Response.Error(e)
                }
                trySend(levelResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun searchSubLevels(idLevel: String): Flow<List<SubLevel>> = callbackFlow {
        val snapshotListener = subLevelRef.whereEqualTo(SubLevel.ID_LEVEL, idLevel)
            .addSnapshotListener { snapshot, _ ->
                val subLevelModel = snapshot?.toObjects(SubLevel::class.java)
                    ?: ArrayList<SubLevel>()
                trySend(subLevelModel)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

}