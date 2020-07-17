package com.devundefined.menulistsample.presentation.menulist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devundefined.menulistsample.domain.MenuService

class MenuListViewModelFactory(private val menuService: MenuService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MenuService::class.java).newInstance(menuService)
    }
}