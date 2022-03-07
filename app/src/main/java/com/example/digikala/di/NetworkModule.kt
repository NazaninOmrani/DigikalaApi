package com.example.digikala.di

import com.example.digikala.business.data.cache.CacheDataSource
import com.example.digikala.business.data.network.NetworkDataSource
import com.example.digikala.business.data.network.NetworkDataSourceImpl
import com.example.digikala.business.intractors.GetProduct
import com.example.digikala.framework.datasource.network.ProductRetrofitService
import com.example.digikala.framework.datasource.network.ProductRetrofitServiceImpl
import com.example.digikala.framework.datasource.network.mappers.NetworkMapper
import com.example.digikala.framework.datasource.network.rerofit.ProductRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideGetBlogs(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetProduct {
        return GetProduct(cacheDataSource, networkDataSource)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/")
    }

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit.Builder): ProductRetrofit {
        return retrofit
            .build()
            .create(ProductRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        productRetrofit: ProductRetrofit
    ): ProductRetrofitService {
        return ProductRetrofitServiceImpl(productRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        productRetrofitService: ProductRetrofitService,
        networkMapper: NetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(productRetrofitService, networkMapper)
    }

}