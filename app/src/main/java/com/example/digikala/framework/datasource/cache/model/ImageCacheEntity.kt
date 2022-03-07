package com.example.digikala.framework.datasource.cache.model

import io.realm.RealmObject

open class ImageCacheEntity : RealmObject() {
    var src: String? = ""
}
