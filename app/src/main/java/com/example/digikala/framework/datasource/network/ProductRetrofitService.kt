package com.example.digikala.framework.datasource.network

import com.example.digikala.framework.datasource.network.model.ProductsNetworkEntity

interface ProductRetrofitService {

    suspend fun get(): List<ProductsNetworkEntity>
}