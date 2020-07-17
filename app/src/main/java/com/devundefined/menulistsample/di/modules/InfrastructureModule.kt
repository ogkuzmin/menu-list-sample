package com.devundefined.menulistsample.di.modules

import android.content.Context
import androidx.room.Room
import com.devundefined.menulistsample.infrastructure.cache.CacheKeyRepository
import com.devundefined.menulistsample.infrastructure.cache.SystemTimeProvider
import com.devundefined.menulistsample.infrastructure.cache.SystemTimeProviderImpl
import com.devundefined.menulistsample.infrastructure.network.MenuApi
import com.devundefined.menulistsample.infrastructure.persistence.AppDatabase
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideMenuApi(): MenuApi {
        return Retrofit.Builder()
            .baseUrl(MenuApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MenuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "menu-list-db").build()

    @Provides
    @Singleton
    fun provideCacheKeyRepository(appDatabase: AppDatabase): CacheKeyRepository =
        CacheKeyRepositoryImpl(appDatabase.cacheDao())

    @Provides
    @Singleton
    fun provideSystemTimeProvider(): SystemTimeProvider = SystemTimeProviderImpl()
}