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

    override fun back() {
        runInActivity {
            if (supportFragmentManager.fragments.any { it is ProductCardFragment }) {
                supportFragmentManager.popBackStack()
            }
        }
    }

    private fun showFragment(fragmentProvider: () -> Fragment) {
        runInActivity {
            supportFragmentManager.beginTransaction().add(R.id.container, fragmentProvider())
                .addToBackStack(null).commit()
        }
    }

    private fun runInActivity(action: MainActivity.() -> Unit) {
        activityRef.get()?.run(action)
    }
}