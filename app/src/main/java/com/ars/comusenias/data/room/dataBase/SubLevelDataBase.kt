package com.ars.comusenias.data.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ars.comusenias.data.room.dao.SubLevelDao
import com.ars.comusenias.domain.models.room.SubLevelEntity

/**
 * Clase abstracta que representa la base de datos de subniveles.
 * Se utiliza para definir todas las entidades y versiones de la base de datos.
 */
@Database(
    entities = [SubLevelEntity::class],
    version = 1,
    exportSchema = false
)
/**
 * Define el DAO de subniveles que se utilizará para interactuar con los datos de los subniveles en la base de datos.
 * @return el DAO de subniveles.
 */
abstract class SubLevelDataBase : RoomDatabase() {
    abstract fun getSubLevelDao(): SubLevelDao
}