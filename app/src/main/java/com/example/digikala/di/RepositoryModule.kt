package com.example.digikala.di

import android.content.Context
import com.example.digikala.data.mapper.CacheMapper
import com.example.digikala.data.repository.LoginRepository
import com.example.digikala.data.repository.ProductRepository
import com.example.digikala.data.mapper.NetworkMapper
import com.example.digikala.data.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        realm: Realm,
        api: ProductApi,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): ProductRepository {
        return ProductRepository(realm, api, cacheMapper, networkMapper)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(
        @ApplicationContext context: Context
    ): LoginRepository {
        return LoginRepository(context)
    }
}