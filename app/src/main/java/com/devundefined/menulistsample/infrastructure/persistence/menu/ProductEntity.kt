package com.devundefined.menulistsample.infrastructure.persistence.menu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val categoryName: String,
    val imageUrl: String,
    val description: String,
    val priceAmount: Double,
    val priceCurrencyCode: String
)