package com.devundefined.menulistsample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.presentation.menulist.MenuListFragment
import com.devundefined.menulistsample.presentation.navigation.Router
import com.devundefined.menulistsample.presentation.navigation.RouterHolder
import com.devundefined.menulistsample.presentation.navigation.RouterImpl

class MainActivity : AppCompatActivity(), RouterHolder {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router = RouterImpl(this)
        RouterHolder.INSTANCE = this
        router.showMenuList()
    }

    override lateinit var router: Router
}