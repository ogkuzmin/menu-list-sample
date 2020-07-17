package com.devundefined.menulistsample.presentation.menulist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devundefined.menulistsample.MenuListSampleApp
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.domain.models.Menu
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MenuListFragment : Fragment(R.layout.fragment_menu_list) {

    private lateinit var viewModel: MenuListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(
                requireActivity(),
                MenuListSampleApp.INSTANCE.appComponent.viewModelFactory()
            ).get(MenuListViewModel::class.java)
    }

    fun showState(state: MenuListScreenState) {
        when (state) {
            MenuListScreenState.Failed -> showLoadingFailed()
            MenuListScreenState.Loading -> showLoading()
            is MenuListScreenState.MenuLoaded -> showMenu(state.menu)
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