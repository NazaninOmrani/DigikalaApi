package com.example.digikala.framework.datasource.cache

import com.example.digikala.framework.datasource.cache.database.ProductDao
import com.example.digikala.framework.datasource.cache.model.ProductCacheEntity
import io.realm.Realm

class ProductDaoServiceImpl
constructor(
    private val  productDao: ProductDao
):ProductDaoService{

    private var realm: Realm = Realm.getDefaultInstance()

    override suspend fun insert(productCacheEntity: ProductCacheEntity) {
        realm.configuration
        return
    }

    override suspend fun get(): List<ProductCacheEntity> {
        return productDao.get()
    }
}