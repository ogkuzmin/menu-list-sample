package com.devundefined.menulistsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devundefined.menulistsample.presentation.menulist.MenuListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, MenuListFragment()).commit()
    }
}