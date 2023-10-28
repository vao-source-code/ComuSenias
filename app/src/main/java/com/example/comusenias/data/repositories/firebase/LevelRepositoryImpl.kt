package com.example.comusenias.data.repositories.firebase

import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.repositories.LevelRepository
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.DelicateCoroutinesApi
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

    override fun searchLevelById(id: String): Flow<LevelModel> = callbackFlow {
        val snapshotListener = levelRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(LevelModel::class.java) ?: LevelModel(
                EMPTY_STRING,
                EMPTY_STRING,
                listOf()
            )
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun searchLevelName(name: String): Flow<LevelModel> = callbackFlow {
        val snapshotListener =
            levelRef.whereEqualTo("name", name).addSnapshotListener { snapshot, _ ->
                val level = snapshot?.first()?.toObject(LevelModel::class.java) ?: LevelModel(
                    EMPTY_STRING,
                    EMPTY_STRING,
                    listOf()
                )
                trySend(level)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getLevels(): Flow<Response<List<LevelModel>>> = callbackFlow {
        val snapshotListener = levelRef.addSnapshotListener { snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val levelResponse = snapshot?.let {
                    val level = snapshot.toObjects(LevelModel::class.java)
                    snapshot.documents.forEachIndexed { index, document ->
                        level[index].id = document.id
                    }
                    val idLevelModelArray = ArrayList<String>()
                    level.forEach { l ->
                        l.subLevel.forEach { id ->
                            idLevelModelArray.add(id.name)
                        }
                    }
                    level.forEach { l ->
                        subLevelRef.whereEqualTo("idLevel", l.id).get().await()
                            .forEach { document ->
                                l.subLevel.add(document.toObject(SubLevelModel::class.java))
                            }
                    }
                    Response.Success(level)
                } ?: Response.Error(e)
                trySend(levelResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun searchSubLevels(idLevel: String): Flow<List<SubLevelModel>> = callbackFlow {
        val snapshotListener = subLevelRef.whereEqualTo("idLevel", idLevel)
            .addSnapshotListener { snapshot, _ ->
                val subLevelModel = snapshot?.toObjects(SubLevelModel::class.java)
                    ?: ArrayList<SubLevelModel>()
                trySend(subLevelModel)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

}