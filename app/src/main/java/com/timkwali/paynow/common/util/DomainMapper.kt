package com.timkwali.paynow.common.util

interface DomainMapper<DomainModel, Dto> {

    suspend fun mapToDomain(entity: DomainModel): Dto
}