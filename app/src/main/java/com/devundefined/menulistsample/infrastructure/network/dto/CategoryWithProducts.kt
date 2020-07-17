package com.devundefined.menulistsample.infrastructure.network.dto

import com.google.gson.annotations.SerializedName

data class CategoryWithProducts(
    @SerializedName("name") val categoryName: String,
    @SerializedName("products") val products: List<ProductDto>
)

