package com.devundefined.menulistsample.di.modules

import com.devundefined.menulistsample.domain.MenuLoadingService
import com.devundefined.menulistsample.domain.MenuRepository
import com.devundefined.menulistsample.domain.MenuService
import com.devundefined.menulistsample.domain.MenuServiceImpl
import com.devundefined.menulistsample.infrastructure.cache.CacheKeyRepository
import com.devundefined.menulistsample.infrastructure.cache.CacheValidator
import com.devundefined.menulistsample.infrastructure.cache.CacheValidatorImpl
import com.devundefined.menulistsample.infrastructure.cache.SystemTimeProvider
import com.devundefined.menulistsample.infrastructure.network.MenuApi
import com.devundefined.menulistsample.infrastructure.network.MenuLoadingServiceImpl
import com.devundefined.menulistsample.infrastructure.persistence.AppDatabase
import com.devundefined.menulistsample.infrastructure.persistence.menu.MenuRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideMenuRepository(appDatabase: AppDatabase): MenuRepository =
        MenuRepositoryImpl(appDatabase.productDao())

    @Provides
    @Singleton
    fun provideCacheValidator(
        cacheKeyRepository: CacheKeyRepository,
        systemTimeProvider: SystemTimeProvider
    ): CacheValidator = CacheValidatorImpl(cacheKeyRepository, systemTimeProvider)

    @Provides
    @Singleton
    fun provideMenuLoadingService(menuApi: MenuApi): MenuLoadingService =
        MenuLoadingServiceImpl(menuApi)

    @Provides
    @Singleton
    fun providerMenuService(
        menuLoadingService: MenuLoadingService,
        menuRepository: MenuRepository,
        cacheValidator: CacheValidator
    ): MenuService = MenuServiceImpl(menuLoadingService, menuRepository, cacheValidator)
}