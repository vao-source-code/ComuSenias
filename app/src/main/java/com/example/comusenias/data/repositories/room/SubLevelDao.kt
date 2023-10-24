package com.example.comusenias.data.repositories.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.comusenias.domain.models.room.SubLevelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubLevelDao {
    @Query("SELECT * FROM subLevel_table")
    fun getSubLevel(): Flow<List<SubLevelEntity>>

    @Query("SELECT * FROM subLevel_table WHERE idSubLevel = :idSubLevel")
    fun getSubLevelById(idSubLevel: String): SubLevelEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubLevel(subLevelEntity: List<SubLevelEntity>)

    @Update
    suspend fun updateSubLevel(subLevelEntity: SubLevelEntity)

    @Delete
    suspend fun deleteSubLevel(subLevelEntity: SubLevelEntity)
}