package com.example.comusenias.core.di

import android.content.Context
import com.example.comusenias.data.repositories.data_storage.UserDataStorageRepositoryImp
import com.example.comusenias.domain.repositories.UserDataStorageRepository
import com.example.comusenias.domain.use_cases.test.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.test.GetUserValue
import com.example.comusenias.domain.use_cases.test.PutUserValue
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext app: Context): UserDataStorageRepository {
        return UserDataStorageRepositoryImp(app)
    }

    @Provides
    fun provideContext(@ApplicationContext app: Context): Context {
        return app
    }

    @Provides
    fun provideDataUserStorageFactory(repository: UserDataStorageRepository) =
        DataUserStorageFactory(
            putUserValue = PutUserValue(repository),
            getUserValue = GetUserValue(repository)
        )

}