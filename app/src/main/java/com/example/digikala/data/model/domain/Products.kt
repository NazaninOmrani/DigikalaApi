package com.example.digikala.data.model.domain

data class Products(
    var id: Int?,
    var name: String?,
    var images: List<Images>?,
    var price: String?,
    var regularPrice: String?,
)