package com.example.comusenias.data.repositories

import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.models.game.Game
import com.example.comusenias.domain.repositories.GameRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class GameRepositoryImp @Inject constructor(
        @Named(FirebaseConstants.SUB_LEVEL_COLLECTION) private val sublevelsRef: CollectionReference,
        @Named(FirebaseConstants.GAME_COLLECTION) private val gameRef: CollectionReference,
) : GameRepository {

    override suspend fun searchGame(idGame: String): Flow<Game> = callbackFlow{
        val snapshotListener = gameRef.document(idGame).addSnapshotListener { snapshot, _ ->
            val gameModel = snapshot?.toObject(Game::class.java) ?: Game()
            gameModel.id = snapshot?.id ?: ""
            trySend(gameModel)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun searchBySublevelId(idSubLevel : String): Flow<Game> = callbackFlow {
        val snapshotListener = sublevelsRef.document(idSubLevel).addSnapshotListener { snapshot, _ ->
            val gameModel = snapshot?.toObject(Game::class.java) ?: Game()
            gameModel.id = snapshot?.id ?: ""
            trySend(gameModel)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }


}