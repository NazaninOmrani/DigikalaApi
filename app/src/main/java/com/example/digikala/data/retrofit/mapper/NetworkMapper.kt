package com.example.digikala.data.retrofit.mapper

import com.example.digikala.util.EntityMapper
import com.example.digikala.data.domain.Images
import com.example.digikala.data.domain.Products
import com.example.digikala.data.retrofit.model.ImagesNetworkEntity
import com.example.digikala.data.retrofit.model.ProductsNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<ProductsNetworkEntity, Products> {

    override fun mapFromEntity(entity: ProductsNetworkEntity): Products {
        return Products(
            entity.id,
            entity.name,
            entity.images?.map { Images(it.src) },
            entity.price,
            entity.regularPrice
        )
    }

    override fun mapToEntity(domainModel: Products): ProductsNetworkEntity {
        return ProductsNetworkEntity(
            domainModel.id,
            domainModel.name,
            domainModel.images?.map { ImagesNetworkEntity(it.src) },
            domainModel.price,
            domainModel.regularPrice
        )
    }

    fun mapFromEntityList(entities: List<ProductsNetworkEntity>): List<Products> {
        return entities.map { mapFromEntity(it) }
    }
}