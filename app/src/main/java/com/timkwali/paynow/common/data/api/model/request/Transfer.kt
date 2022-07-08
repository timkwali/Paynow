package com.timkwali.paynow.common.data.api.model.request


import com.google.gson.annotations.SerializedName

data class Transfer(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("reason")
    val reason: String?,
    @SerializedName("recipient")
    val recipient: String?,
    @SerializedName("source")
    val source: String?
)