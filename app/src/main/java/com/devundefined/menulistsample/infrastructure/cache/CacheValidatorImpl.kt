package com.devundefined.menulistsample.infrastructure.cache

class CacheValidatorImpl(private val cacheKeyRepository: CacheKeyRepository) : CacheValidator {
    override fun isValid(cacheKey: String, cacheValidTimeInterval: Long): Boolean {
        return cacheKeyRepository.findByCacheKey(cacheKey) != null
    }

    override fun invalidateCache(cacheKey: String) {
        TODO("Not yet implemented")
    }
}