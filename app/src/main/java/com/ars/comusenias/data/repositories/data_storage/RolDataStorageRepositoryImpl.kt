package com.ars.comusenias.data.repositories.data_storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.domain.repositories.RolDataStorageRepository
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = PreferencesConstant.PREFERENCE_ROL_CURRENT)

class RolDataStorageRepositoryImpl @Inject constructor(private val context: Context) :
    RolDataStorageRepository {

    override suspend fun putRolValue(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun getRolValue(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val json = withContext(IO) {
                context.dataStore.data.first()
            }
            json[preferenceKey]
        } catch (e: Exception) {
            e.printStackTrace()
            EMPTY_STRING
        }
    }
}