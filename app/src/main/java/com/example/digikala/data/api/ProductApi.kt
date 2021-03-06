package com.example.digikala.data.api

import com.example.digikala.data.model.network.ProductsNetworkEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 *This class is for retrofit api calling
 */
interface ProductApi {

    @GET("products")
    suspend fun getProducts(@QueryMap queries: Map<String, String>): List<ProductsNetworkEntity>
}