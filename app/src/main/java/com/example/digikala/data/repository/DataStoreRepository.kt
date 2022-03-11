package com.example.digikala.data.repository

interface DataStoreRepository {
    suspend fun putUserName(key: String, value: String)
    suspend fun putPassword(key: String, value: String)
    suspend fun getUserName(key: String): String?
    suspend fun getPassword(key: String): String?
}