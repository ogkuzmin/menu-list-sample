package com.devundefined.menulistsample.infrastructure.cache

interface CacheKeyRepository {
    fun findByCacheKey(cacheKey: String): CacheKeyEntry?
    fun save(cacheKeyEntry: CacheKeyEntry)
}