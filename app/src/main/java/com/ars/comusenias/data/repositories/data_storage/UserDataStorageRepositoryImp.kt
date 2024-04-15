package com.ars.comusenias.data.repositories.data_storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ars.comusenias.constants.PreferencesConstant.PREFERENCE_USER
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.repositories.UserDataStorageRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = PREFERENCE_USER)

class UserDataStorageRepositoryImp @Inject constructor(private val context: Context) :
    UserDataStorageRepository {
    override suspend fun putUserValue(key: String, value: UserModel) {

        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value.toJson()
        }
    }

    override suspend fun getUserValue(key: String): UserModel? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            val json = preferences[preferenceKey]
            UserModel.fromJson(json!!)
        } catch (e: Exception) {
            e.printStackTrace()
            UserModel()
        }
    }
}