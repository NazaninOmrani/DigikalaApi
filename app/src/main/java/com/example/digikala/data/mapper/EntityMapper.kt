package com.example.digikala.data.mapper

/**
 *This Interface class is for mapping data  from Entity AND to entity
 *
 */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

}