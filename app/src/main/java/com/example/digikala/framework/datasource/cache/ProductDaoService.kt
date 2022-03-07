package com.example.digikala.framework.datasource.cache

import com.example.digikala.framework.datasource.cache.model.ProductCacheEntity

interface ProductDaoService {

    suspend fun insert(productCacheEntity: ProductCacheEntity)

    suspend fun get(): List<ProductCacheEntity>
}