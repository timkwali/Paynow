package com.timkwali.paynow.common.data.api

import com.timkwali.paynow.common.data.api.model.request.Transfer
import com.timkwali.paynow.common.data.api.model.request.TransferRecipient
import com.timkwali.paynow.common.data.api.model.response.*
import com.timkwali.paynow.common.util.Constants
import retrofit2.http.*

interface PayNowApi {

    @GET("bank")
    suspend fun getBankList(
        @Query("currency") currency: String = "NGN",
        @Header("Authorization") auth: String = "Bearer ${Constants.SECRET_KEY}"
    ): ResponseModel<List<Bank>>

    @GET("bank/resolve")
    suspend fun verifyAccountNumber(
        @Query("account_number") accountNumber: String,
        @Query("bank_code") bankCode: String,
        @Header("Authorization") auth: String = "Bearer ${Constants.SECRET_KEY}"
    ): ResponseModel<VerifyAccountResponse>

    @POST("transferrecipient")
    suspend fun createTransferRecipient(
        @Body transferRecipient: TransferRecipient,
        @Header("Authorization") auth: String = "Bearer ${Constants.SECRET_KEY}"
    ): ResponseModel<TransferRecipientResponse>

    @POST("transfer")
    suspend fun transfer(
        @Body transfer: Transfer,
        @Header("Authorization") auth: String = "Bearer ${Constants.SECRET_KEY}"
    ): ResponseModel<TransferResponse>
}