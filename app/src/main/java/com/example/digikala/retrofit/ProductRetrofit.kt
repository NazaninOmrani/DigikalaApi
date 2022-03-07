package com.example.digikala.retrofit

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductRetrofit {

    @GET("products")
    suspend fun getProducts(@QueryMap queries: Map<String, String>): List<ProductsNetworkEntity>
}