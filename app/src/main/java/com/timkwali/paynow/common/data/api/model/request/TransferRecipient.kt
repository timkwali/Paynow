package com.timkwali.paynow.common.data.api.model.request


import com.google.gson.annotations.SerializedName

data class TransferRecipient(
    @SerializedName("account_number")
    val accountNumber: String?,
    @SerializedName("bank_code")
    val bankCode: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)