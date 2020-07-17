package com.devundefined.menulistsample.di.modules

import android.content.Context
import com.devundefined.menulistsample.domain.MenuService
import com.devundefined.menulistsample.presentation.menulist.MenuListViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideViewModelFactory(menuService: MenuService) = MenuListViewModelFactory(menuService)
}