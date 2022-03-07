package com.example.digikala.framework.datasource.cache.mappers

import com.example.digikala.business.domain.model.Images
import com.example.digikala.business.domain.model.Products
import com.example.digikala.business.domain.util.EntityMapper
import com.example.digikala.framework.datasource.cache.model.ProductCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<ProductCacheEntity, Products> {

    override fun mapFromEntity(entity: ProductCacheEntity): Products {
        return Products(
            entity.id,
            entity.name,
            listOf(entity.images).map { Images(it?.src) },
            entity.price,
            entity.regularPrice
        )
    }

    override fun mapToEntity(domainModel: Products): ProductCacheEntity {
        val productCacheEntity = ProductCacheEntity()
        productCacheEntity.id = domainModel.id
        domainModel.images?.map { productCacheEntity.images }
        productCacheEntity.name = domainModel.name
        productCacheEntity.price = domainModel.price
        productCacheEntity.regularPrice = domainModel.regularPrice
        return productCacheEntity
    }

    fun mapFromEntityList(entities: List<ProductCacheEntity>): List<Products> {
        return entities.map { mapFromEntity(it) }
    }

}