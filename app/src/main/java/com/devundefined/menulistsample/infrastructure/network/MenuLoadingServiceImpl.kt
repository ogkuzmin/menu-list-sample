package com.devundefined.menulistsample.infrastructure.network

import com.devundefined.menulistsample.domain.MenuLoadingService
import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.infrastructure.Try

class MenuLoadingServiceImpl(private val menuApi: MenuApi) : MenuLoadingService {
    override fun loadMenu(): Try<Menu> {
        TODO("Not yet implemented")
    }
}