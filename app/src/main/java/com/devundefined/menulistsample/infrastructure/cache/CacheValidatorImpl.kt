package com.devundefined.menulistsample.infrastructure.cache

class CacheValidatorImpl(
    private val cacheKeyRepository: CacheKeyRepository,
    private val systemTimeProvider: SystemTimeProvider
) : CacheValidator {
    override fun isValid(cacheKey: String, cacheValidTimeInterval: Long): Boolean {
        val cacheEntry = cacheKeyRepository.findByCacheKey(cacheKey)
        return if (cacheEntry == null) {
            false
        } else {
            systemTimeProvider.getSystemTime() - cacheEntry.cacheTimeStamp < cacheValidTimeInterval
        }
    }

    override fun invalidateCache(cacheKey: String) {
        cacheKeyRepository.save(CacheKeyEntry(cacheKey, systemTimeProvider.getSystemTime()))
    }
}