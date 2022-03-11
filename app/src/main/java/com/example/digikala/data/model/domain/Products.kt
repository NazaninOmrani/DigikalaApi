package com.example.digikala.data.model.domain
/**
 *This class is Domain model for Products
 */
data class Products(
    var id: Int?,
    var name: String?,
    var images: List<Images>?,
    var price: String?,
    var regularPrice: String?,
)