package com.timkwali.paynow.transfer.domain.usecases

import com.timkwali.paynow.common.data.api.model.response.VerifyAccountResponse
import com.timkwali.paynow.common.domain.repository.PayNowRepository
import com.timkwali.paynow.common.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VerifyAccountNumber @Inject constructor(
    private val repository: PayNowRepository
) {

    suspend operator fun invoke(accountNumber: String, bankCode: String) = flow<Resource<VerifyAccountResponse>> {
        try {
            repository.verifyAccountNumber(accountNumber, bankCode).collect {
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