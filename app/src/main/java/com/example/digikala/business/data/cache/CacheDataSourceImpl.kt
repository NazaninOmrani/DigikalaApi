package com.example.digikala.business.data.cache

import com.example.digikala.business.domain.model.Products
import com.example.digikala.framework.datasource.cache.ProductDaoService
import com.example.digikala.framework.datasource.cache.mappers.CacheMapper

class CacheDataSourceImpl
constructor(
    private val productDaoService: ProductDaoService,
    private val cacheMapper: CacheMapper
) : CacheDataSource {

    override suspend fun insertList(productsList: List<Products>) {
        for (product in productsList)
            productDaoService.insert(cacheMapper.mapToEntity(product))
    }

    override suspend fun get(): List<Products> {
        return cacheMapper.mapFromEntityList(productDaoService.get())
    }

}