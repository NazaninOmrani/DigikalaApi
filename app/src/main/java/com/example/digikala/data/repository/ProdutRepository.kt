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

class ProdutRepository
@Inject
constructor(
    private val realm: Realm,
    private val productRetrofit: ProductRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    val cachedProducts = realm.where(ProductCacheEntity::class.java).findAll()
    val CONSUMER_KEY = "ck_7c028a04c9faf616410b09e2ab90b1884c875d01"
    val CONSUMER_SECRET = "cs_8c39f626780f01d135719f64214fd092b848f4aa"

    private val mQueries: Map<String, String> = object : HashMap<String, String>() {
        init {
            put("consumer_key", CONSUMER_KEY)
            put("consumer_secret", CONSUMER_SECRET)
        }
    }

    @DelicateCoroutinesApi
    suspend fun getProduct(): Flow<DataState<List<Products>>> = flow {
        try {
            if (cachedProducts.size == 0 || cachedProducts.equals(null)) {
                emit(DataState.Loading)
                kotlinx.coroutines.delay(500)
                val networkProducts = productRetrofit.getProducts(mQueries)
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
}