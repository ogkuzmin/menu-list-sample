package com.devundefined.menulistsample.presentation.menulist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.devundefined.menulistsample.MenuListSampleApp
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.domain.models.Menu
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateFlow
            .onEach { showState(it) }
            .launchIn(lifecycleScope)
        lifecycleScope.launch {
            viewModel.intentChannel.send(MenuListIntent.Attach)
        }
    }

    fun showState(state: MenuListScreenState) {
        when (state) {
            MenuListScreenState.Failed -> showLoadingFailed()
            MenuListScreenState.Loading -> showLoading()
            is MenuListScreenState.MenuLoaded -> showMenu(state.menu)
        }
    }

    private fun showMenu(menu: Menu) {
        android.util.Log.d("MenuList", "show menu")
    }

    private fun showLoading() {
        android.util.Log.d("MenuList", "show loading")
    }

    private fun showLoadingFailed() {
        android.util.Log.d("MenuList", "show loading failed")
    }
}