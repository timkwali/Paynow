package com.timkwali.paynow.common.data.api.model.response.transaction


import com.google.gson.annotations.SerializedName

data class Transactions(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("status")
    val status: Boolean?
)