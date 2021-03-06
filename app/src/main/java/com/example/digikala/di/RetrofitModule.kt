package com.example.digikala.di

import com.example.digikala.data.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This Module is for provide RetrofitBuilder AND ProductApi instance for app
 */
@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/")
    }

    @Singleton
    @Provides
    fun provideProductApi(
        retrofit: Retrofit.Builder
    ): ProductApi {
        return retrofit
            .build()
            .create(ProductApi::class.java)
    }
}