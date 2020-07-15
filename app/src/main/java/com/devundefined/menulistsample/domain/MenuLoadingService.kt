package com.devundefined.menulistsample.domain

import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.infrastructure.Try

interface MenuLoadingService {
    fun loadMenu(): Try<Menu>
}