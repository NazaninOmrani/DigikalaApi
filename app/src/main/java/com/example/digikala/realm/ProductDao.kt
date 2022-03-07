package com.example.digikala.realm


interface ProductDao {

    suspend fun insert(productCacheEntity: ProductCacheEntity)

    suspend fun get(): List<ProductCacheEntity>
}