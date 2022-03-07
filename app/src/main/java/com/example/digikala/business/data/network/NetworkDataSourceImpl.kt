package com.example.digikala.business.data.network

import com.example.digikala.business.domain.model.Products
import com.example.digikala.framework.datasource.network.ProductRetrofitService
import com.example.digikala.framework.datasource.network.mappers.NetworkMapper

class NetworkDataSourceImpl
constructor(
    private val productRetrofitService: ProductRetrofitService,
    private val networkMapper: NetworkMapper
) : NetworkDataSource {

    override suspend fun get(): List<Products> {
        return networkMapper.mapFromEntityList(productRetrofitService.get())
    }
}