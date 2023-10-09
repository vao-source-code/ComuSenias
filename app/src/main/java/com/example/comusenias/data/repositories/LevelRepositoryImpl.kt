package com.example.comusenias.data.repositories

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
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class LevelRepositoryImpl @Inject constructor(
        @Named(FirebaseConstants.LEVEL_COLLECTION) private val levelRef: CollectionReference,
        @Named(FirebaseConstants.SUB_LEVEL_COLLECTION) private val subLevelRef: CollectionReference,
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
                val levelResponse = if (snapshot != null) {
                    val level = snapshot.toObjects(LevelModel::class.java)
                    snapshot.documents.forEachIndexed { index, document ->
                        level[index].id = document.id
                    }
                    val idLevelModelArray = ArrayList<String>()
                    level.forEach { l ->
                        l.subNivel.forEach { id ->
                            idLevelModelArray.add(id)
                        }
                    }
                    level.forEach { l ->
                        subLevelRef.whereEqualTo(SubLevelModel.ID_LEVEL, l.id).get().await().forEach { document ->
                            l.subLevelModel.add(document.toObject(SubLevelModel::class.java))
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


    override fun getWhereSubLevels(idLevel: String): Flow<List<SubLevelModel>> = callbackFlow {
        val snapshotListener = subLevelRef.whereEqualTo("levelId", idLevel).addSnapshotListener { snapshot, _ ->
            val subLevelModel = snapshot?.toObjects(SubLevelModel::class.java)
                    ?: ArrayList<SubLevelModel>()
            trySend(subLevelModel)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

}