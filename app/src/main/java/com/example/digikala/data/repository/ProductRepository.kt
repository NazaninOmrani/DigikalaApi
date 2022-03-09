package com.example.digikala.data.repository

import com.example.digikala.data.domain.Products
import com.example.digikala.data.realm.mapper.CacheMapper
import com.example.digikala.data.realm.model.ProductCacheEntity
import com.example.digikala.data.retrofit.mapper.NetworkMapper
import com.example.digikala.data.retrofit.ProductRetrofit
import com.example.digikala.util.DataState
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.HashMap
import javax.inject.Inject

class ProductRepository
@Inject
constructor(
    private val realm: Realm,
    private val productRetrofit: ProductRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    private val cachedProducts = realm.where(ProductCacheEntity::class.java).findAll()
    private val hashMap: HashMap<String, String> = HashMap<String, String>()

    suspend fun getProduct(): Flow<DataState<List<Products>>> = flow {
        try {
            if (cachedProducts.size == 0 || cachedProducts.equals(null)) {
                emit(DataState.Loading)
                kotlinx.coroutines.delay(500)
                val networkProducts = productRetrofit.getProducts(provideRequestQueries())
                val products = networkMapper.mapFromEntityList(networkProducts)
                for (product in products) {
                    realm.executeTransactionAwait { realm1 ->
                        realm1.copyToRealm(cacheMapper.mapToEntity(product))
                    }
                }
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedProducts)))
            } else {
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedProducts)))
            }

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    private fun provideRequestQueries(): Map<String, String> {
        val CONSUMER_KEY = "ck_7c028a04c9faf616410b09e2ab90b1884c875d01"
        val CONSUMER_SECRET = "cs_8c39f626780f01d135719f64214fd092b848f4aa"
        hashMap["consumer_key"] = CONSUMER_KEY
        hashMap["consumer_secret"] = CONSUMER_SECRET
        return hashMap
    }
}