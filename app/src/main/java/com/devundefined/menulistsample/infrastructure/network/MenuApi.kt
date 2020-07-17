package com.devundefined.menulistsample.infrastructure.network

import com.devundefined.menulistsample.infrastructure.network.dto.MenuDto
import retrofit2.http.GET

interface MenuApi {
    @GET("/")
    fun getMenu(): MenuDto
}