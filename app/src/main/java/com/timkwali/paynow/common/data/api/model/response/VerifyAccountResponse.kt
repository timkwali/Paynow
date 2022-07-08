package com.timkwali.paynow.common.data.api.model.response


import com.google.gson.annotations.SerializedName

data class VerifyAccountResponse(
    @SerializedName("account_name")
    val accountName: String?,
    @SerializedName("account_number")
    val accountNumber: String?,
    @SerializedName("bank_id")
    val bankId: Int?
)