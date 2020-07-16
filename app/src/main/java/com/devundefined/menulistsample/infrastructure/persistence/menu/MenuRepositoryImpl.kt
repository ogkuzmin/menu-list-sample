package com.devundefined.menulistsample.infrastructure.persistence.menu

import com.devundefined.menulistsample.domain.MenuRepository
import com.devundefined.menulistsample.domain.models.Menu

class MenuRepositoryImpl(private val dao: ProductDao) : MenuRepository {
    override fun getMenu(): Menu {
        TODO("Not yet implemented")
    }

    override fun saveMenu(menu: Menu) {
        TODO("Not yet implemented")
    }
}