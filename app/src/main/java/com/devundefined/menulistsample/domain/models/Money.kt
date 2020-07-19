package com.devundefined.menulistsample.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.util.*

@Parcelize
data class Money(val amount: BigDecimal, val currency: Currency) : Parcelable