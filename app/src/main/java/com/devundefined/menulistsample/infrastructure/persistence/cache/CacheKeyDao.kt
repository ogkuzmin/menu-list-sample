package com.devundefined.menulistsample.infrastructure.persistence.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devundefined.menulistsample.infrastructure.cache.CacheKeyEntry

@Dao
interface CacheKeyDao {

    @Query("SELECT * FROM cachekeyentryentity WHERE cacheKey == :cacheKey")
    fun findByCacheKey(cacheKey: String): CacheKeyEntry?

    @Insert
    fun insert(entity: CacheKeyEntryEntity)

    @Query("DELETE FROM cachekeyentryentity WHERE cacheKey == :cacheKey")
    fun removeByCacheKey(cacheKey: String)
}