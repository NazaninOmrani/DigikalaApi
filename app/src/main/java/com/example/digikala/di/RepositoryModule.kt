package com.example.digikala.di

import com.example.digikala.realm.CacheMapper
import com.example.digikala.realm.ProductDao
import com.example.digikala.repository.MainRepository
import com.example.digikala.retrofit.NetworkMapper
import com.example.digikala.retrofit.ProductRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        productDao: ProductDao,
        retrofit: ProductRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(productDao, retrofit, cacheMapper, networkMapper)
    }

}