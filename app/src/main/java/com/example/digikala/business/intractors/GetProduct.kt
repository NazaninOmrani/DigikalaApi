package com.example.digikala.business.intractors

import com.example.digikala.business.data.cache.CacheDataSource
import com.example.digikala.business.data.network.NetworkDataSource
import com.example.digikala.business.domain.model.Products
import com.example.digikala.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProduct
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun execute(): Flow<DataState<List<Products>>> = flow {
        emit(DataState.Loading)
        val networkProducts = networkDataSource.get()
        emit(DataState.Idle)
        cacheDataSource.insertList(networkProducts)
        val cachedProducts = cacheDataSource.get()
        emit(DataState.Success(cachedProducts))
    }
}