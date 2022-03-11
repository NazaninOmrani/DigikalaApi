package com.example.digikala.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.digikala.data.model.datastore.UserInfo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 *This class is Repository for create DataStore Instance AND
 * create keys then save and get info from DataStore
 *
 */
class LoginRepository
@Inject
constructor(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "LoginDataStore")

    companion object {
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
    }

    suspend fun putUserInfo(userInfo: UserInfo) {
        context.dataStore.edit {
            it[USERNAME] = userInfo.userName
            it[PASSWORD] = userInfo.password
        }
    }

    suspend fun getUserInfo(): UserInfo? {
        val preferences: Preferences = context.dataStore.data.first()
        return preferences[USERNAME]?.let {
            preferences[PASSWORD]?.let { it1 ->
                UserInfo(
                    it,
                    it1
                )
            }
        }
    }
}