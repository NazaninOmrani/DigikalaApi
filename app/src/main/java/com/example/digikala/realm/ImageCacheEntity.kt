package com.example.digikala.realm

import io.realm.RealmObject

open class ImageCacheEntity(
    var src: String? = ""
) : RealmObject()