package com.example.digikala.retrofit

import com.google.gson.annotations.SerializedName

data class ProductsNetworkEntity(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("images")
    var images: List<ImagesNetworkEntity>? = null,


    @SerializedName("price")
    var price: String? = null,

    @SerializedName("regular_price")
    var regularPrice: String? = null,
)