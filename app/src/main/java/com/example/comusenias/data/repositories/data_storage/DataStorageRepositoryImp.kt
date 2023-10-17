package com.example.comusenias.data.repositories.data_storage

import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.repositories.DataStorageRepository
import com.example.comusenias.domain.repositories.UserDataStorageRepository
import javax.inject.Inject

class DataStorageRepositoryImp @Inject constructor(
    private val userPreferences: UserDataStorageRepository
) : DataStorageRepository {

    override suspend fun putValueUser(key: String, value: String) {
        userPreferences.putUserValue(key, UserModel.fromJson(value))
    }

    override suspend fun getValueUser(key: String): UserModel? {
        return userPreferences.getUserValue(key)
    }
}