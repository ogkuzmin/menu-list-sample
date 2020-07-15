package com.devundefined.menulistsample.domain.models

data class Product(
    val id: String,
    val name: String,
    val category: Category,
    val imageUrl: String,
    val description: String,
    val price: Money
)