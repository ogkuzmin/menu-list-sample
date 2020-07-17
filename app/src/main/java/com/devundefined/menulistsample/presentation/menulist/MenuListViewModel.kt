package com.devundefined.menulistsample.presentation.menulist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devundefined.menulistsample.domain.MenuService
import com.devundefined.menulistsample.infrastructure.Try
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class MenuListViewModel(private val menuService: MenuService) : ViewModel() {
    private val mutableStateFlow =
        MutableStateFlow<MenuListScreenState>(MenuListScreenState.Loading)

    val intentChannel = Channel<MenuListIntent>(Channel.Factory.UNLIMITED)
    val stateFlow: StateFlow<MenuListScreenState>
        get() = mutableStateFlow

    init {
        viewModelScope.launch {
            handleIntents()
        }
    }

    private suspend fun handleIntents() {
        intentChannel.consumeAsFlow().collect(::handleIntent)
    }

    private suspend fun handleIntent(intent: MenuListIntent) {
        when (intent) {
            MenuListIntent.Attach -> {
                if (stateFlow.value !is MenuListScreenState.MenuLoaded) {
                    loadMenu()
                }
            }
            MenuListIntent.Reload -> loadMenu()
        }

    }

    private suspend fun loadMenu() = withContext(Dispatchers.IO) {
        mutableStateFlow.value = MenuListScreenState.Loading
        when (val result = menuService.getMenu()) {
            is Try.Success -> mutableStateFlow.value = MenuListScreenState.MenuLoaded(result.value)
            is Try.Failure -> mutableStateFlow.value = MenuListScreenState.Failed
        }
    }
}