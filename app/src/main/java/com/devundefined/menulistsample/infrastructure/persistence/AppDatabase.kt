package com.devundefined.menulistsample.infrastructure.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyDao
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyEntryEntity

@Database(entities = [CacheKeyEntryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheKeyDao
}