package com.timkwali.paynow.common.data.api.model.response.transaction


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("customer_code")
    val customerCode: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("metadata")
    val metadata: Any?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("risk_action")
    val riskAction: String?
)