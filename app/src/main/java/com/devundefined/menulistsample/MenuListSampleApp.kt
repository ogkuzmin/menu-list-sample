package com.devundefined.menulistsample

import android.app.Application
import com.devundefined.menulistsample.di.AppComponent
import com.devundefined.menulistsample.di.DaggerAppComponent
import com.devundefined.menulistsample.di.modules.AppModule

class MenuListSampleApp : Application() {
    companion object {
        lateinit var INSTANCE: MenuListSampleApp
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}