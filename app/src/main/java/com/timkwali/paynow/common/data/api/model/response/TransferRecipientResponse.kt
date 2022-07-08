package com.timkwali.paynow.common.data.api.model.response


import com.google.gson.annotations.SerializedName

data class TransferRecipientResponse(
    @SerializedName("active")
    val active: Boolean?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("details")
    val details: Details?,
    @SerializedName("domain")
    val domain: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("integration")
    val integration: Int?,
    @SerializedName("is_deleted")
    val isDeleted: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("recipient_code")
    val recipientCode: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

data class Details(
    @SerializedName("account_name")
    val accountName: Any?,
    @SerializedName("account_number")
    val accountNumber: String?,
    @SerializedName("authorization_code")
    val authorizationCode: Any?,
    @SerializedName("bank_code")
    val bankCode: String?,
    @SerializedName("bank_name")
    val bankName: String?
)