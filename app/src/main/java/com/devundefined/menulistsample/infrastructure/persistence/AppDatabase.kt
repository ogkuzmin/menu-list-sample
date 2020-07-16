package com.devundefined.menulistsample.infrastructure.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyDao
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyEntryEntity
import com.devundefined.menulistsample.infrastructure.persistence.menu.ProductDao
import com.devundefined.menulistsample.infrastructure.persistence.menu.ProductEntity

@Database(entities = [CacheKeyEntryEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheKeyDao
    abstract fun productDao(): ProductDao
}