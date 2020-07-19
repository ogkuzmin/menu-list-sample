package com.devundefined.menulistsample.presentation.navigation

import com.devundefined.menulistsample.domain.models.Product

interface Router {
    fun back()

    fun showMenuList()

    fun showProductScreen(product: Product)
}