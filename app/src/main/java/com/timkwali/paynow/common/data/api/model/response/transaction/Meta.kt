package com.timkwali.paynow.common.data.api.model.response.transaction


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("perPage")
    val perPage: Int?,
    @SerializedName("skipped")
    val skipped: Int?,
    @SerializedName("total")
    val total: Int?
)