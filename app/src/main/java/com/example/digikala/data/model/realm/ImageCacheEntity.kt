package com.example.digikala.data.model.realm

import io.realm.RealmObject
/**
 *This class is Realm model for Image entity
 */
open class ImageCacheEntity(
    var src: String? = ""
) : RealmObject()