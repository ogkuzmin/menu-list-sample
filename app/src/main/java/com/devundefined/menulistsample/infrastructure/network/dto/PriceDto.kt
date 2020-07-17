package com.devundefined.menulistsample.infrastructure.network.dto

import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("amount") val amount: Double,
    @SerializedName("currency") val currencyCode: String
)