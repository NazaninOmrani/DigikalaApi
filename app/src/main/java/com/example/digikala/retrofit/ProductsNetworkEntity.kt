package com.example.digikala.retrofit

import com.google.gson.annotations.SerializedName

data class ProductsNetworkEntity(

    @SerializedName("id")
    var id: Int?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("images")
    var images: List<ImagesNetworkEntity>?,

    @SerializedName("price")
    var price: String?,

    @SerializedName("regular_price")
    var regularPrice: String?,
)