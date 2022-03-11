package com.example.digikala.data.model.datastore

/**
 *This class is model for saving user login info (name and pass)
 * into DataStore Preferences
 */
data class UserInfo(
    var userName: String,
    var password: String
)
