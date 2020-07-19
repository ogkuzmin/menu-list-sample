package com.devundefined.menulistsample.presentation.navigation

import androidx.fragment.app.Fragment
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.domain.models.Product
import com.devundefined.menulistsample.presentation.MainActivity
import com.devundefined.menulistsample.presentation.menulist.MenuListFragment
import com.devundefined.menulistsample.presentation.productcard.ProductCardFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.ref.WeakReference

class RouterImpl(activity: MainActivity) : Router {
    private val activityRef: WeakReference<MainActivity> = WeakReference(activity)

    @ExperimentalCoroutinesApi
    override fun showMenuList() {
        showFragment { MenuListFragment() }
    }

    override fun showProductScreen(product: Product) {
        showFragment { ProductCardFragment.newInstance(product) }
    }

    private fun showFragment(fragmentProvider: () -> Fragment) {
        activityRef.get()?.let { mainActivity ->
            mainActivity.supportFragmentManager.beginTransaction()
                .add(R.id.container, fragmentProvider()).commit()
        }
    }
}