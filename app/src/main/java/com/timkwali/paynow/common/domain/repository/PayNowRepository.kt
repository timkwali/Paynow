package com.timkwali.paynow.common.domain.repository

import com.timkwali.paynow.common.data.api.model.request.Transfer
import com.timkwali.paynow.common.data.api.model.request.TransferRecipient
import com.timkwali.paynow.common.data.api.model.response.*
import kotlinx.coroutines.flow.Flow

interface PayNowRepository {

    suspend fun getBankList(): Flow<ResponseModel<List<Bank>>>
    suspend fun verifyAccountNumber(accountNumber: String, bankCode: String): Flow<ResponseModel<VerifyAccountResponse>>
    suspend fun createTransferRecipient(transferRecipient: TransferRecipient): Flow<ResponseModel<TransferRecipientResponse>>
    suspend fun transfer(transfer: Transfer): Flow<ResponseModel<TransferResponse>>
}