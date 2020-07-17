package com.devundefined.menulistsample.di

import com.devundefined.menulistsample.di.modules.AppModule
import com.devundefined.menulistsample.di.modules.DomainModule
import com.devundefined.menulistsample.di.modules.InfrastructureModule
import com.devundefined.menulistsample.presentation.menulist.MenuListViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, InfrastructureModule::class])
@Singleton
interface AppComponent {
    fun viewModelFactory(): MenuListViewModelFactory
}