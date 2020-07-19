package com.devundefined.menulistsample.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val category: Category,
    val imageUrl: String,
    val description: String,
    val price: Money
) : Parcelable