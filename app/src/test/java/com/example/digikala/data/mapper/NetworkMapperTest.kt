package com.example.digikala.data.mapper

import com.example.digikala.data.model.domain.Images
import com.example.digikala.data.model.domain.Products
import com.example.digikala.data.model.network.ImagesNetworkEntity
import com.example.digikala.data.model.network.ProductsNetworkEntity
import org.junit.Assert.assertEquals
import org.junit.Test


class NetworkMapperTest {

    private val networkMapper = NetworkMapper()

    @Test
    fun mapFromEntity() {
        val a = networkMapper.mapFromEntity(getProductNetworkEntity())
        assertEquals(a, getProducts())
    }

    @Test
    fun mapToEntity() {
        val a = networkMapper.mapToEntity(getProducts())
        assertEquals(a, getProductNetworkEntity())
    }

    @Test
    fun mapFromEntityList() {
        val a = networkMapper.mapFromEntityList(listOf(getProductNetworkEntity()))
        assertEquals(a, listOf(getProducts()))
    }

    private fun getProductNetworkEntity(): ProductsNetworkEntity {
        return ProductsNetworkEntity(
            id = 1,
            name = "name",
            images = listOf(ImagesNetworkEntity("src")),
            price = "1000",
            regularPrice = "2000"
        )
    }

    private fun getProducts(): Products {
        return Products(
            id = 1,
            name = "name",
            images = listOf(Images("src")),
            price = "1000",
            regularPrice = "2000"
        )
    }
}