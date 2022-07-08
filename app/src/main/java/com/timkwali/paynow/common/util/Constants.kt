package com.timkwali.paynow.common.util

import com.timkwali.paynow.BuildConfig

object Constants {
    const val BASE_URL = "https://api.paystack.co/"
    const val SECRET_KEY = BuildConfig.SECRET_KEY
    const val NIGERIA_CURRENCY = "NGN"
    const val NIGERIA_MONEY_TYPE = "nuban"
    const val TRANSFER_SOURCE = "balance"
    const val LOWER_AMOUNT_LIMIT = 100
    const val UPPER_AMOUNT_LIMIT= 10000000
}