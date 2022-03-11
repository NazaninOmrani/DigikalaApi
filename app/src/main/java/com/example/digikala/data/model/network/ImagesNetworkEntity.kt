package com.example.digikala.data.model.network

import com.google.gson.annotations.SerializedName
/**
 *This class is Retrofit model for image entity
 */
data class ImagesNetworkEntity(
    @SerializedName("src")
    var src: String?
)
