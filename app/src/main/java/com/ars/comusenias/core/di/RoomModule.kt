package com.ars.comusenias.core.di

import android.content.Context
import androidx.room.Room
import com.ars.comusenias.data.repositories.firebase.SubLevelRepositoryImpl
import com.ars.comusenias.data.room.dao.SubLevelDao
import com.ars.comusenias.data.room.dataBase.SubLevelDataBase
import com.ars.comusenias.domain.repositories.SubLevelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    /**
     * Proporciona la base de datos de subniveles.
     * @param context el contexto de la aplicación.
     * @return la base de datos de subniveles.
     */
    @Singleton
    @Provides
    fun provideSubLevelRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SubLevelDataBase::class.java, DATABASE_NAME)
            .build()

    /**
     * Proporciona el DAO de subniveles.
     * @param db la base de datos de subniveles.
     * @return el DAO de subniveles.
     */
    @Singleton
    @Provides
    fun provideSubLevelDao(db: SubLevelDataBase) = db.getSubLevelDao()

    /**
     * Proporciona el repositorio de subniveles.
     * @param subLevelDao el DAO de subniveles.
     * @return el repositorio de subniveles.
     */
    @Provides
    fun provideSubLevelRepository(
        subLevelDao: SubLevelDao
    ): SubLevelRepository = SubLevelRepositoryImpl(
        subLevelDao = subLevelDao
    )

    companion object {
        const val DATABASE_NAME = "subLevel_database"
    }
}