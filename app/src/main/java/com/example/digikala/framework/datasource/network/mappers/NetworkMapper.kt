package com.example.digikala.framework.datasource.network.mappers

import com.example.digikala.business.domain.model.Images
import com.example.digikala.business.domain.model.Products
import com.example.digikala.business.domain.util.EntityMapper
import com.example.digikala.framework.datasource.network.model.ImagesNetworkEntity
import com.example.digikala.framework.datasource.network.model.ProductsNetworkEntity
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
}