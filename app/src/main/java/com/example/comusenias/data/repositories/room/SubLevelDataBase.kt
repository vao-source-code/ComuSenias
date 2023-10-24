package com.example.comusenias.data.repositories.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comusenias.domain.models.room.SubLevelEntity

/**
 * Clase abstracta que representa la base de datos de subniveles.
 * Se utiliza para definir todas las entidades y versiones de la base de datos.
 */
@Database(
    entities = [SubLevelEntity::class],
    version = 1,
)
/**
 * Define el DAO de subniveles que se utilizará para interactuar con los datos de los subniveles en la base de datos.
 * @return el DAO de subniveles.
 */
abstract class SubLevelDataBase : RoomDatabase() {
    abstract fun getSubLevelDao(): SubLevelDao
}