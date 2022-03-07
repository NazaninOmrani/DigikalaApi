package com.example.digikala.business.data.cache

import com.example.digikala.business.domain.model.Products

interface CacheDataSource {

    suspend fun insertList(productsList: List<Products>)

    suspend fun get(): List<Products>
}