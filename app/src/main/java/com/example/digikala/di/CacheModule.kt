package com.example.digikala.di

import android.content.Context
import androidx.room.Room
import com.example.digikala.business.data.cache.CacheDataSource
import com.example.digikala.business.data.cache.CacheDataSourceImpl
import com.example.digikala.business.domain.model.Products
import com.example.digikala.business.domain.util.EntityMapper
import com.example.digikala.framework.datasource.cache.ProductDaoService
import com.example.digikala.framework.datasource.cache.ProductDaoServiceImpl
import com.example.digikala.framework.datasource.cache.database.ProductDao
import com.example.digikala.framework.datasource.cache.database.ProductDataBase
import com.example.digikala.framework.datasource.cache.mappers.CacheMapper
import com.example.digikala.framework.datasource.cache.model.ProductCacheEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<ProductCacheEntity, Products> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideProductDb(@ApplicationContext context: Context): ProductDataBase {
        return Room
            .databaseBuilder(
                context,
                BlogDatabase::class.java,
                BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDAO(productDataBase: ProductDataBase): ProductDao {
        return productDataBase.productDao()
    }

    @Singleton
    @Provides
    fun provideProductDaoService(
       productDao: ProductDao
    ):ProductDaoService{
        return ProductDaoServiceImpl(productDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        productDaoService: ProductDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource{
        return CacheDataSourceImpl(productDaoService, cacheMapper)
    }

}