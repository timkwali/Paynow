package com.timkwali.paynow.common.data.repository

import com.timkwali.paynow.common.data.api.PayNowApi
import com.timkwali.paynow.common.data.api.model.request.Transfer
import com.timkwali.paynow.common.data.api.model.request.TransferRecipient
import com.timkwali.paynow.common.data.api.model.response.*
import com.timkwali.paynow.common.data.api.model.response.transaction.Transactions
import com.timkwali.paynow.common.domain.repository.PayNowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PayNowRepositoryImpl @Inject constructor(
    private val payNowApi: PayNowApi
): PayNowRepository {
    override suspend fun getBankList(): Flow<ResponseModel<List<Bank>>> {
        return flowOf(payNowApi.getBankList())
    }

    override suspend fun verifyAccountNumber(
        accountNumber: String,
        bankCode: String
    ): Flow<ResponseModel<VerifyAccountResponse>> {
        return flowOf(payNowApi.verifyAccountNumber(accountNumber, bankCode))
    }

    override suspend fun createTransferRecipient(
        transferRecipient: TransferRecipient
    ): Flow<ResponseModel<TransferRecipientResponse>> {
        return flowOf(payNowApi.createTransferRecipient(transferRecipient))
    }

    override suspend fun transfer(
        transfer: Transfer
    ): Flow<ResponseModel<TransferResponse>> {
        return flowOf(payNowApi.transfer(transfer))
    }

    override suspend fun getTransactions(): Flow<Transactions> {
        return flowOf(payNowApi.getTransactions())
    }
}