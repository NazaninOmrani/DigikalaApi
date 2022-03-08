package com.example.digikala.di

import com.example.digikala.realm.CacheMapper
import com.example.digikala.repository.MainRepository
import com.example.digikala.retrofit.NetworkMapper
import com.example.digikala.retrofit.ProductRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        realm: Realm,
        retrofit: ProductRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(realm, retrofit, cacheMapper, networkMapper)
    }
}