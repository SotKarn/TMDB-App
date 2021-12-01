package com.example.tmdbapp.utils


interface IEntityMapper<Entity, DomainEntity>
{
    fun mapToLocal(entity: Entity) : DomainEntity
    fun mapFromLocal(entity: DomainEntity) : Entity
}