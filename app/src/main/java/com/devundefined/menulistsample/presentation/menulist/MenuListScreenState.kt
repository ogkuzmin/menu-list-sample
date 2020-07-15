package com.devundefined.menulistsample.presentation.menulist

import com.devundefined.menulistsample.domain.models.Menu

sealed class MenuListScreenState

object Loading : MenuListScreenState()
object Failed : MenuListScreenState()
class MenuLoaded(val menu: Menu) : MenuListScreenState()
