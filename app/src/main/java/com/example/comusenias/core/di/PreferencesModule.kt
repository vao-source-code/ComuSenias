package com.example.comusenias.core.di

import android.content.Context
import com.example.comusenias.data.repositories.data_storage.RolDataStorageRepositoryImpl
import com.example.comusenias.data.repositories.data_storage.UserDataStorageRepositoryImp
import com.example.comusenias.domain.repositories.RolDataStorageRepository
import com.example.comusenias.domain.repositories.UserDataStorageRepository
import com.example.comusenias.domain.use_cases.shared_preferences.DataRolStorageFactory
import com.example.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.shared_preferences.GetRolValue
import com.example.comusenias.domain.use_cases.shared_preferences.GetUserValue
import com.example.comusenias.domain.use_cases.shared_preferences.PutRolValue
import com.example.comusenias.domain.use_cases.shared_preferences.PutUserValue
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

    @Singleton
    @Provides
    fun provideRolPreferences(@ApplicationContext app: Context): RolDataStorageRepository {
        return RolDataStorageRepositoryImpl(app)
    }


    @Provides
    fun provideDataRolStorageFactory(repository: RolDataStorageRepository) =
        DataRolStorageFactory(
            putRolValue = PutRolValue(repository),
            getRolValue = GetRolValue(repository)
        )


    @Provides
    fun provideDataUserStorageFactory(repository: UserDataStorageRepository) =
        DataUserStorageFactory(
            putUserValue = PutUserValue(repository),
            getUserValue = GetUserValue(repository)
        )

}