package com.example.digikala.framework.datasource.cache.database

import com.example.digikala.framework.datasource.cache.model.ProductCacheEntity

interface ProductDao {

    suspend fun insert(productCacheEntity: ProductCacheEntity): Long

    suspend fun get(): List<ProductCacheEntity>
}