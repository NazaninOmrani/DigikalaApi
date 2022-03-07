package com.example.digikala.framework.datasource.cache.model

import io.realm.RealmObject

open class ProductCacheEntity : RealmObject() {
    var id: Int? = 0
    var name: String? = ""
    var images: ImageCacheEntity? = ImageCacheEntity()
    var price: String? = ""
    var regularPrice: String? = ""
}