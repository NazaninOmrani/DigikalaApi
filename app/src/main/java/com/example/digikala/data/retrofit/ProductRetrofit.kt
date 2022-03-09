package com.example.digikala.data.retrofit

import com.example.digikala.data.retrofit.model.ProductsNetworkEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductRetrofit {

    @GET("products")
    suspend fun getProducts(@QueryMap queries: Map<String, String>): List<ProductsNetworkEntity>
}