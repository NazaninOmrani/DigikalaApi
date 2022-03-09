package com.example.digikala.di

import com.example.digikala.data.retrofit.ProductRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
    fun provideProductService(retrofit: Retrofit.Builder): ProductRetrofit {
        return retrofit
            .build()
            .create(ProductRetrofit::class.java)
    }
}