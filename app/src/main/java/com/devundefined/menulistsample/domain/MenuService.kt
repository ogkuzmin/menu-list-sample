package com.devundefined.menulistsample.domain

import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.infrastructure.Try

interface MenuService {
    fun getMenu(): Try<Menu>
}

class MenuServiceImpl(
    private val menuLoadingService: MenuLoadingService,
    private val menuRepository: MenuRepository
) : MenuService {
    override fun getMenu(): Try<Menu> {
        return menuLoadingService.loadMenu()
            .onSuccess(menuRepository::saveMenu)
    }
}