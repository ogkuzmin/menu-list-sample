package com.devundefined.menulistsample.presentation.navigation

interface RouterHolder {
    companion object {
        var INSTANCE: RouterHolder? = null
    }
    val router: Router
}