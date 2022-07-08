package com.timkwali.paynow.transactions.domain.usecases

import com.timkwali.paynow.common.domain.repository.PayNowRepository
import com.timkwali.paynow.common.util.Resource
import com.timkwali.paynow.transactions.domain.model.Transaction
import com.timkwali.paynow.transactions.domain.model.TransactionMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTransactions @Inject constructor(
    private val repository: PayNowRepository
) {

    suspend operator fun invoke() = flow<Resource<List<Transaction>>> {
        try {
            repository.getTransactions().collect {
                if(it.status == true) {
                    emit(Resource.Success(
                        it.data?.let {
                            it.map { TransactionMapper().mapToDomain(it) }
                        } ?: emptyList()
                    ))
                } else {
                    emit(Resource.Error(it.message ?: "Something went wrong."))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong."))
        }
    }
}