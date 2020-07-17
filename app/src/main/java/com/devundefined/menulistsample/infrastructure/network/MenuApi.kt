package com.devundefined.menulistsample.infrastructure.network

import com.devundefined.menulistsample.infrastructure.network.dto.MenuDto
import retrofit2.http.GET

interface MenuApi {
    companion object {
        const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com"
    }

    @GET("/")
    suspend fun getMenu(): MenuDto
}