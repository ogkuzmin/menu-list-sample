package com.devundefined.menulistsample.infrastructure.cache

interface CacheValidator {
    fun isValid(cacheKey: String, cacheValidTimeInterval: Long): Boolean
    fun invalidateCache(cacheKey: String)
}