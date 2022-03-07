package com.example.digikala.business.data.network

import com.example.digikala.business.domain.model.Products

interface NetworkDataSource {

    suspend fun get(): List<Products>
}