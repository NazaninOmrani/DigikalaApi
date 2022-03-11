package com.example.digikala.data.model.realm

import io.realm.RealmObject

open class ImageCacheEntity(
    var src: String? = ""
) : RealmObject()