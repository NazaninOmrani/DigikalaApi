package com.example.digikala.data.model.realm

import io.realm.RealmObject
/**
 *This class is Realm model for Product entity
 */
open class ProductCacheEntity(
    var id: Int? = 0,
    var name: String? = "",
    var images: ImageCacheEntity? = ImageCacheEntity(),
    var price: String? = "",
    var regularPrice: String? = ""
) : RealmObject()