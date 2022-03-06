package com.example.digikala.model

data class Products(
    var id: Int? = null,
    var name: String? = null,
    var images: List<Images>? = null,
    var price: String? = null,
    var regularPrice: String? = null,
)