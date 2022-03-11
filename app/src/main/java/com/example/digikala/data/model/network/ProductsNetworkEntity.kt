package com.example.digikala.data.model.network

import com.google.gson.annotations.SerializedName
/**
 *This class is Retrofit model for Products entity
 */
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