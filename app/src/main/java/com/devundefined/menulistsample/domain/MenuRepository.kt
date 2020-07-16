package com.devundefined.menulistsample.domain

import com.devundefined.menulistsample.domain.models.Menu

interface MenuRepository {
    fun getMenu(): Menu
    fun saveMenu(menu: Menu)
}