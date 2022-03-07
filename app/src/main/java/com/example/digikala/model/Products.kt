package com.example.digikala.model

data class Products(
    var id: Int?,
    var name: String?,
    var images: List<Images>?,
    var price: String?,
    var regularPrice: String?,
)