package com.example.digikala.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.digikala.data.database.datastore.model.UserInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.intellij.lang.annotations.Flow
import javax.inject.Inject

class DataStoreManager
@Inject
constructor(val context: Context) {

     val Context.loginDataStore: DataStore<Preferences> by preferencesDataStore(name = "userinfo")

    companion object {
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
    }

    suspend fun putString(userInfo: UserInfo) {
        context.loginDataStore.edit {
            it[USERNAME] = userInfo.userName
            it[PASSWORD] = userInfo.password
        }
    }

    suspend fun getString(): UserInfo? {
        val preferences: Preferences = context.loginDataStore.data.first()
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