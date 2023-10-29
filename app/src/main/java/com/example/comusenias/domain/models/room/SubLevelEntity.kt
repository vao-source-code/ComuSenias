package com.example.comusenias.domain.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.ui.theme.STATUS
import com.example.comusenias.presentation.ui.theme.SUB_LEVEL_ID
import com.example.comusenias.presentation.ui.theme.SUB_LEVEL_TABLE

/**
 * Clase que representa una entidad de subnivel en la base de datos.
 * @property idSubLevel El identificador único del subnivel.
 * @property status El estado del subnivel.
 */
@Entity(tableName = SUB_LEVEL_TABLE)
data class SubLevelEntity(
    @PrimaryKey
    @ColumnInfo(name = SUB_LEVEL_ID) val idSubLevel: String,
    @ColumnInfo(name = STATUS) var status: StatusGame = StatusGame.BLOCKED,
)