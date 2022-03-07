package com.example.digikala.framework.datasource.network

import com.example.digikala.framework.datasource.network.model.ProductsNetworkEntity
import com.example.digikala.framework.datasource.network.rerofit.ProductRetrofit
import java.util.HashMap

class ProductRetrofitServiceImpl
constructor(
    private val productRetrofit: ProductRetrofit
) : ProductRetrofitService {

    val CONSUMER_KEY = "ck_7c028a04c9faf616410b09e2ab90b1884c875d01"
    val CONSUMER_SECRET = "cs_8c39f626780f01d135719f64214fd092b848f4aa"
    private val mQueries: Map<String, String> = object : HashMap<String, String>() {
        init {
            put("consumer_key", CONSUMER_KEY)
            put("consumer_secret", CONSUMER_SECRET)
        }
    }

    override suspend fun get(): List<ProductsNetworkEntity> {
        return productRetrofit.getProducts(mQueries)
    }
}