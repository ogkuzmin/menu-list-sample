package com.devundefined.menulistsample.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

@Parcelize
data class Money(val amount: BigDecimal, val currency: Currency) : Parcelable {
    override fun toString(): String {
        return NumberFormat.getCurrencyInstance()
            .apply { currency = this@Money.currency }
            .format(amount)
    }
}