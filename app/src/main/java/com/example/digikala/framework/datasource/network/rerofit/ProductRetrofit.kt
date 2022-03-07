package com.example.digikala.framework.datasource.network.rerofit

import com.example.digikala.framework.datasource.network.model.ProductsNetworkEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductRetrofit {

    @GET("products")
    suspend fun getProducts(@QueryMap queries: Map<String, String>): Call<List<ProductsNetworkEntity>>
}