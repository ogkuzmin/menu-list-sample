package com.devundefined.menulistsample.infrastructure.persistence.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CacheKeyDao {

    @Query("SELECT * FROM CACHEKEYENTRYENTITY WHERE cacheKey == :cacheKey")
    fun findByCacheKey(cacheKey: String): CacheKeyEntryEntity?

    @Insert
    fun insert(entity: CacheKeyEntryEntity)

    @Query("DELETE FROM CACHEKEYENTRYENTITY WHERE cacheKey == :cacheKey")
    fun removeByCacheKey(cacheKey: String)
}