package com.devundefined.menulistsample.infrastructure.cache

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CacheValidatorTests {

    companion object {
        private const val CACHE_KEY = "CACHE_KEY"
        private const val CACHE_TIME_INTERVAL = 1000L
    }

    private lateinit var sut: CacheValidator

    @Mock
    lateinit var cacheKeyRepository: CacheKeyRepository

    @Mock
    lateinit var systemTimeProvider: SystemTimeProvider

    @Before
    fun before() {
        sut = CacheValidatorImpl(cacheKeyRepository, systemTimeProvider)
    }

    @Test
    fun whenIsValid_ifCacheRepositoryDoesNotContainAny_shouldReturnFalse() {
        whenever(cacheKeyRepository.findByCacheKey(any())).thenReturn(null)

        assertFalse(sut.isValid(CACHE_KEY, CACHE_TIME_INTERVAL))
    }

    @Test
    fun whenIsValid_ifCacheRepoContainsCacheEntry_andDifferenceBetweenCurrentTimeAndCachedTimeStampMoreThanRequired_shouldReturnFalse() {
        val currentTime = 10000L
        val difference = CACHE_TIME_INTERVAL + 100
        val cachedTimeStamp = currentTime - difference
        val cacheKeyEntry = CacheKeyEntry(CACHE_KEY, cachedTimeStamp)
        whenever(systemTimeProvider.getSystemTime()).thenReturn(currentTime)
        whenever(cacheKeyRepository.findByCacheKey(CACHE_KEY)).thenReturn(cacheKeyEntry)

        assertFalse(sut.isValid(CACHE_KEY, CACHE_TIME_INTERVAL))
    }

    @Test
    fun whenIsValid_ifCacheRepoContainsCacheEntry_andDifferenceBetweenCurrentTimeAndCachedTimeStampLessThanRequired_shouldReturnReturn() {
        val currentTime = 10000L
        val difference = CACHE_TIME_INTERVAL - 100
        val cachedTimeStamp = currentTime - difference
        val cacheKeyEntry = CacheKeyEntry(CACHE_KEY, cachedTimeStamp)
        whenever(systemTimeProvider.getSystemTime()).thenReturn(currentTime)
        whenever(cacheKeyRepository.findByCacheKey(CACHE_KEY)).thenReturn(cacheKeyEntry)

        assertTrue(sut.isValid(CACHE_KEY, CACHE_TIME_INTERVAL))
    }

    @Test
    fun whenInvalidateCache_shouldSaveCacheEntryToRepository_withTimeStampThatEqualsToSystemTime() {
        val currentTime = 10000L
        whenever(systemTimeProvider.getSystemTime()).thenReturn(currentTime)

        sut.invalidateCache(CACHE_KEY)

        verify(cacheKeyRepository).save(argThat { cacheKey == CACHE_KEY && cacheTimeStamp == currentTime })
    }
}