package com.timkwali.paynow.transfer.domain.usecases

import com.timkwali.paynow.common.data.api.model.request.TransferRecipient
import com.timkwali.paynow.common.data.api.model.response.TransferRecipientResponse
import com.timkwali.paynow.common.domain.repository.PayNowRepository
import com.timkwali.paynow.common.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateTransferRecipient @Inject constructor(
    private val repository: PayNowRepository
) {

    suspend operator fun invoke(transferRecipient: TransferRecipient) = flow<Resource<TransferRecipientResponse>> {
        try {
            repository.createTransferRecipient(transferRecipient).collect {
                if(it.status) {
                    emit(Resource.Success(it.data))
                } else {
                    emit(Resource.Error(it.message))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }

    }
}