package com.devundefined.menulistsample.infrastructure.сache

import com.devundefined.menulistsample.infrastructure.cache.CacheKeyRepository
import com.devundefined.menulistsample.infrastructure.cache.CacheValidator
import com.devundefined.menulistsample.infrastructure.cache.CacheValidatorImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertFalse
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

    @Before
    fun before() {
        sut = CacheValidatorImpl(cacheKeyRepository)
    }

    @Test
    fun whenIsValid_ifCacheRepositoryDoesNotContainAny_shouldReturnFalse() {
        whenever(cacheKeyRepository.findByCacheKey(any())).thenReturn(null)

        assertFalse(sut.isValid(CACHE_KEY, CACHE_TIME_INTERVAL))
    }
}