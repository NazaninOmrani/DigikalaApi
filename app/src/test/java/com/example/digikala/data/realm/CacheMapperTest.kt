package com.example.digikala.data.realm

import com.example.digikala.data.domain.Images
import com.example.digikala.data.domain.Products
import com.example.digikala.data.realm.mapper.CacheMapper
import com.example.digikala.data.realm.model.ImageCacheEntity
import com.example.digikala.data.realm.model.ProductCacheEntity
import org.junit.Assert.*
import org.junit.Test

class CacheMapperTest {
    private val cacheMapper = CacheMapper()

    @Test
    fun mapFromEntity() {
        val a = cacheMapper.mapFromEntity(getProductCacheEntity())
        assertEquals(a, getProducts())
    }

    @Test
    fun mapToEntity() {
        val b = cacheMapper.mapToEntity(getProducts())
        assertEquals(b.id, getProductCacheEntity().id)
    }

    private fun getProductCacheEntity(): ProductCacheEntity {
        return ProductCacheEntity(
            id = 0,
            name = "name",
            images = ImageCacheEntity(src = "src"),
            price = "1000",
            regularPrice = "2000"
        )
    }

    private fun getProducts(): Products {
        return Products(
            id = 0,
            name = "name",
            images = listOf(Images("src")),
            price = "1000",
            regularPrice = "2000"
        )
    }
}