package com.devundefined.menulistsample.infrastructure.cache

interface CacheKeyRepo {
    fun findByCacheKey(cacheKey: String): CacheKeyEntry
    fun save(cacheKeyEntry: CacheKeyEntry)
}