package com.example.digikala.realm

import io.realm.RealmObject

open class ProductCacheEntity : RealmObject() {
    var id: Int? = 0
    var name: String? = ""
    var images: ImageCacheEntity? = ImageCacheEntity()
    var price: String? = ""
    var regularPrice: String? = ""
}