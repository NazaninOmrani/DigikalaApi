package com.example.digikala.data.realm.model

import io.realm.RealmObject

open class ImageCacheEntity(
    var src: String? = ""
) : RealmObject()