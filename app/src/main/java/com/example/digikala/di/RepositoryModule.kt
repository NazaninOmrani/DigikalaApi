package com.example.digikala.di

import com.example.digikala.data.realm.mapper.CacheMapper
import com.example.digikala.data.repository.ProdutRepository
import com.example.digikala.data.retrofit.mapper.NetworkMapper
import com.example.digikala.data.retrofit.ProductRetrofit
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
    ): ProdutRepository {
        return ProdutRepository(realm, retrofit, cacheMapper, networkMapper)
    }
}