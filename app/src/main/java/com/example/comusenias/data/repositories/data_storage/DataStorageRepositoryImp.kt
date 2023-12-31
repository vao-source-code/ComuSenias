package com.example.comusenias.data.repositories.data_storage

import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.repositories.DataStorageRepository
import com.example.comusenias.domain.repositories.RolDataStorageRepository
import com.example.comusenias.domain.repositories.UserDataStorageRepository
import javax.inject.Inject

class DataStorageRepositoryImp @Inject constructor(
    private val userPreferences: UserDataStorageRepository,
    private val rolePreferences: RolDataStorageRepository
) : DataStorageRepository {

    override suspend fun putValueUser(key: String, value: String) {
        userPreferences.putUserValue(key, UserModel.fromJson(value))
    }

    override suspend fun getValueUser(key: String): UserModel? {
        return userPreferences.getUserValue(key)
    }

    override suspend fun putValueRole(key: String, value: String) {
        rolePreferences.putRolValue(key, value)
    }

    override suspend fun getValueRole(key: String): String? {
        return rolePreferences.getRolValue(key)
    }
}