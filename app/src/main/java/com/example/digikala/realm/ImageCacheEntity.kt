package com.example.digikala.realm

import io.realm.RealmObject

open class ImageCacheEntity : RealmObject() {
    var src: String? = ""
}
