package com.devundefined.menulistsample.infrastructure.persistence

import androidx.room.Database
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyDao
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyEntryEntity

@Database(entities = [CacheKeyEntryEntity::class], version = 1)
abstract class AppDatabase {
    abstract fun cacheDao(): CacheKeyDao
}