package com.devundefined.menulistsample.infrastructure

interface CacheValidator {
    fun isValid(cacheKey: String, cacheValidTimeInterval: Long): Boolean
}