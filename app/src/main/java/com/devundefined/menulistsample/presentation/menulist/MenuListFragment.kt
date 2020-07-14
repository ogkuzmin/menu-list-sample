package com.devundefined.menulistsample.presentation.menulist

import androidx.fragment.app.Fragment
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.domain.Menu

class MenuListFragment : Fragment(R.layout.fragment_menu_list) {

    fun showState(state: MenuListScreenState) {
        when(state) {
            Failed -> showLoadingFailed()
            Loading -> showLoading()
            is MenuLoaded -> showMenu(state.menu)
        }
    }

    private fun showMenu(menu: Menu) {
        TODO("Not yet implemented")
    }

    private fun showLoading() {
        TODO("Not yet implemented")
    }

    private fun showLoadingFailed() {
        TODO("Not yet implemented")
    }
}