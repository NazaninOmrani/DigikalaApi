package com.example.digikala.repository

import com.example.digikala.model.Products
import com.example.digikala.realm.CacheMapper
import com.example.digikala.realm.ProductDao
import com.example.digikala.retrofit.NetworkMapper
import com.example.digikala.retrofit.ProductRetrofit
import com.example.digikala.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.HashMap

class MainRepository
constructor(
    private val productDao: ProductDao,
    private val productRetrofit: ProductRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    val CONSUMER_KEY = "ck_7c028a04c9faf616410b09e2ab90b1884c875d01"
    val CONSUMER_SECRET = "cs_8c39f626780f01d135719f64214fd092b848f4aa"
    private val mQueries: Map<String, String> = object : HashMap<String, String>() {
        init {
            put("consumer_key", CONSUMER_KEY)
            put("consumer_secret", CONSUMER_SECRET)
        }
    }

    suspend fun getProduct(): Flow<DataState<List<Products>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val networkProducts = productRetrofit.getProducts(mQueries)
            val products = networkMapper.mapFromEntityList(networkProducts)
            for (product in products) {
                productDao.insert(cacheMapper.mapToEntity(product))
            }
            val cachedProducts = productDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedProducts)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}