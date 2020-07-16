package com.devundefined.menulistsample.infrastructure.persistence.cache

import com.devundefined.menulistsample.infrastructure.cache.CacheKeyEntry
import com.devundefined.menulistsample.infrastructure.cache.CacheKeyRepository

class CacheKeyRepositoryImpl(private val dao: CacheKeyDao) : CacheKeyRepository {

    override fun findByCacheKey(cacheKey: String): CacheKeyEntry? {
        TODO("Not yet implemented")
    }

    override fun save(cacheKeyEntry: CacheKeyEntry) {
        TODO("Not yet implemented")
    }
}