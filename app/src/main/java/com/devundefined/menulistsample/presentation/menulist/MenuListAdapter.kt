package com.devundefined.menulistsample.presentation.menulist

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.presentation.productlist.ProductListFragment

class MenuListAdapter(val menu: Menu, fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = menu.size

    override fun createFragment(position: Int): Fragment {
        return ProductListFragment.newInstance(menu.keys.elementAt(position))
    }
}