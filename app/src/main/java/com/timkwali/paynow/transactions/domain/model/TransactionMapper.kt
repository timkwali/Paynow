package com.timkwali.paynow.transactions.domain.model

import com.timkwali.paynow.common.data.api.model.response.transaction.Data
import com.timkwali.paynow.common.util.DomainMapper

class TransactionMapper: DomainMapper<Data, Transaction> {
    override suspend fun mapToDomain(entity: Data): Transaction {
        return Transaction(
            entity.id ?: 0,
            entity.status ?: "",
            "${entity.customer?.firstName} ${entity.customer?.lastName}",
            entity.reference ?: "",
            entity.amount ?: 0,
            entity.createdAt ?: ""
        )
    }

}