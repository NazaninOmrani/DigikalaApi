package com.example.digikala.di

import com.example.digikala.business.data.cache.CacheDataSource
import com.example.digikala.business.data.network.NetworkDataSource
import com.example.digikala.business.intractors.GetProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Singleton
    @Provides
    fun provideGetProducts(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetProduct {
        return GetProduct(cacheDataSource, networkDataSource)
    }
}