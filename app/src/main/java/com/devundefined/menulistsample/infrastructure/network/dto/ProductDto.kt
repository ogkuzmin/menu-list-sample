package com.devundefined.menulistsample.infrastructure.network.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val imageUrlPath: String,
    @SerializedName("description") val description: String,
    @SerializedName("salePrice") val price: PriceDto
)