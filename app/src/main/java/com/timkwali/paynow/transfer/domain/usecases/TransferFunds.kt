package com.timkwali.paynow.transfer.domain.usecases

import com.timkwali.paynow.common.data.api.model.request.Transfer
import com.timkwali.paynow.common.data.api.model.response.TransferResponse
import com.timkwali.paynow.common.domain.repository.PayNowRepository
import com.timkwali.paynow.common.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransferFunds @Inject constructor(
    private val repository: PayNowRepository
) {

    suspend operator fun invoke(transfer: Transfer) = flow<Resource<TransferResponse>> {
        try {
            repository.transfer(transfer).collect {
                if(it.status) {
                    emit(Resource.Success(it.data))
                } else {
                    emit(Resource.Error(it.message))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong."))
        }
    }
}