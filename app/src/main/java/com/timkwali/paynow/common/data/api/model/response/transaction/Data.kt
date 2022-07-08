package com.timkwali.paynow.common.data.api.model.response.transaction


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("authorization")
    val authorization: Authorization?,
    @SerializedName("channel")
    val channel: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("createdAt")
    val createdAt2: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("customer")
    val customer: Customer?,
    @SerializedName("domain")
    val domain: String?,
    @SerializedName("fees")
    val fees: Any?,
    @SerializedName("gateway_response")
    val gatewayResponse: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("ip_address")
    val ipAddress: Any?,
    @SerializedName("log")
    val log: Any?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("metadata")
    val metadata: Any?,
    @SerializedName("paid_at")
    val paidAt: Any?,
    @SerializedName("paidAt")
    val paidAt2: String?,
    @SerializedName("plan")
    val plan: Plan?,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("requested_amount")
    val requestedAmount: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("timeline")
    val timeline: Any?
)