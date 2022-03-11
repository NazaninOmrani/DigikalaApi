package com.example.digikala.data.database.datastore.model

import javax.inject.Inject

data class UserInfo
@Inject
constructor(
    var userName: String,
    var password: String
)
