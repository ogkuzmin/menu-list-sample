package com.devundefined.menulistsample.domain

import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.infrastructure.cache.CacheValidator
import com.devundefined.menulistsample.infrastructure.Try
import java.util.concurrent.TimeUnit

interface MenuService {
    fun getMenu(): Try<Menu>
}

class MenuServiceImpl(
    private val menuLoadingService: MenuLoadingService,
    private val menuRepository: MenuRepository,
    private val cacheValidator: CacheValidator
) : MenuService {

    companion object {
        private const val CACHE_KEY = "MENU_CACHE_KEY"
        private val CACHE_TIME_INTERVAL = TimeUnit.MINUTES.toMillis(10)
    }

    override fun getMenu(): Try<Menu> {
        return if (cacheValidator.isValid(CACHE_KEY, CACHE_TIME_INTERVAL)) {
            Try { menuRepository.findMenu() }
        } else {
            menuLoadingService.loadMenu()
                .onSuccess(menuRepository::saveMenu)
                .onSuccess { cacheValidator.invalidateCache(CACHE_KEY) }
        }
    }
}