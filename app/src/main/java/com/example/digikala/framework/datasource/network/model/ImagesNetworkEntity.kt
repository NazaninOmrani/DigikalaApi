package com.example.digikala.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class ImagesNetworkEntity(
    @SerializedName("src")
    var src: String? = null
)
