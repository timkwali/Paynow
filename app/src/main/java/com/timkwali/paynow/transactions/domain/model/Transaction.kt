package com.timkwali.paynow.transactions.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val id: Int,
    val status: String,
    val customerName: String,
    val reference: String,
    val amount: Int,
    val createdAt: String
): Parcelable
