package com.timkwali.paynow.common.data.api.model.response.transaction


import com.google.gson.annotations.SerializedName

data class Authorization(
    @SerializedName("account_name")
    val accountName: String?,
    @SerializedName("authorization_code")
    val authorizationCode: String?,
    @SerializedName("bank")
    val bank: String?,
    @SerializedName("bin")
    val bin: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("card_type")
    val cardType: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("exp_month")
    val expMonth: String?,
    @SerializedName("exp_year")
    val expYear: String?,
    @SerializedName("last4")
    val last4: String?
)