package com.devundefined.menulistsample.presentation.menulist

sealed class MenuListIntent {
    object Attach : MenuListIntent()
    object Reload : MenuListIntent()
}