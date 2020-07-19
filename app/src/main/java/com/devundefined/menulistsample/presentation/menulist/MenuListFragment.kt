package com.devundefined.menulistsample.presentation.menulist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.devundefined.menulistsample.MenuListSampleApp
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.databinding.FragmentMenuListBinding
import com.devundefined.menulistsample.domain.models.Menu
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MenuListFragment : Fragment(R.layout.fragment_menu_list) {

    private lateinit var viewModel: MenuListViewModel

    private lateinit var binding: FragmentMenuListBinding

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
        binding = FragmentMenuListBinding.bind(view)
        viewModel.stateFlow
            .onEach { showState(it) }
            .launchIn(lifecycleScope)
        lifecycleScope.launch {
            viewModel.intentChannel.send(MenuListIntent.Attach)
        }
        binding.retryButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intentChannel.send(MenuListIntent.Reload)
            }
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
        binding.content.visibility = View.VISIBLE
        binding.progress.visibility = View.GONE
        binding.errorContent.visibility = View.GONE
        binding.pager.adapter = MenuListAdapter(menu, this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = menu.keys.elementAt(position).name
        }.attach()
    }

    private fun showLoading() {
        binding.content.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
        binding.errorContent.visibility = View.GONE
    }

    private fun showLoadingFailed() {
        binding.content.visibility = View.GONE
        binding.progress.visibility = View.GONE
        binding.errorContent.visibility = View.VISIBLE
    }
}