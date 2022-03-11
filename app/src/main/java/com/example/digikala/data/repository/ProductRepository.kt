package com.example.digikala.data.repository

import com.example.digikala.data.model.domain.Products
import com.example.digikala.data.mapper.CacheMapper
import com.example.digikala.data.model.realm.ProductCacheEntity
import com.example.digikala.data.mapper.NetworkMapper
import com.example.digikala.data.api.ProductApi
import com.example.digikala.util.ProductsState
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.HashMap
import javax.inject.Inject

/**
 *This class is Repository for init states in ProductsState BY
 * send retrofit request and get data for map to domain AND
 * map data from domain to realm
 * then get data from realm
 */
class ProductRepository
@Inject
constructor(
    private val realm: Realm,
    private val productApi: ProductApi,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    private val cachedProducts = realm.where(ProductCacheEntity::class.java).findAll()
    private val hashMap: HashMap<String, String> = HashMap<String, String>()

    suspend fun getProduct(): Flow<ProductsState<List<Products>>> = flow {
        try {
            if (cachedProducts.size == 0 || cachedProducts.equals(null)) {
                emit(ProductsState.Loading)
                val networkProducts = productApi.getProducts(provideRequestQueries())
                val products = networkMapper.mapFromEntityList(networkProducts)
                for (product in products) {
                    realm.executeTransactionAwait { realm1 ->
                        realm1.copyToRealm(cacheMapper.mapToEntity(product))
                    }
                }
                emit(ProductsState.Success(cacheMapper.mapFromEntityList(cachedProducts)))
            } else {
                emit(ProductsState.Success(cacheMapper.mapFromEntityList(cachedProducts)))
            }

        } catch (e: Exception) {
            emit(ProductsState.Error(e))
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