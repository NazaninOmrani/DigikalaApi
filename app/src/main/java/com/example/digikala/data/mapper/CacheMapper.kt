package com.example.digikala.data.mapper

import com.example.digikala.data.model.domain.Images
import com.example.digikala.data.model.domain.Products
import com.example.digikala.data.model.realm.ImageCacheEntity
import com.example.digikala.data.model.realm.ProductCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<ProductCacheEntity, Products> {

    override fun mapFromEntity(entity: ProductCacheEntity): Products {
        return Products(
            entity.id,
            entity.name,
            listOf(entity.images?.src).map { Images(it) },
            entity.price,
            entity.regularPrice
        )
    }

    override fun mapToEntity(domainModel: Products): ProductCacheEntity {
        return ProductCacheEntity(
            domainModel.id,
            domainModel.name,
            ImageCacheEntity(domainModel.images?.get(0)?.src),
            domainModel.price,
            domainModel.regularPrice
        )
    }

    fun mapFromEntityList(entities: List<ProductCacheEntity>): List<Products> {
        return entities.map { mapFromEntity(it) }
    }
}