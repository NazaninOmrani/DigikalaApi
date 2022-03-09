package com.example.digikala.data.realm.model

import com.example.digikala.data.realm.model.ImageCacheEntity
import io.realm.RealmObject

open class ProductCacheEntity(
    var id: Int? = 0,
    var name: String? = "",
    var images: ImageCacheEntity? = ImageCacheEntity(),
    var price: String? = "",
    var regularPrice: String? = ""
) : RealmObject()