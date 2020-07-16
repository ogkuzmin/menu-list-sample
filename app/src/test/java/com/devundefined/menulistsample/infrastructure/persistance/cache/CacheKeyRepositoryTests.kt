package com.devundefined.menulistsample.infrastructure.persistance.cache

import com.devundefined.menulistsample.infrastructure.cache.CacheKeyEntry
import com.devundefined.menulistsample.infrastructure.cache.CacheKeyRepository
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyDao
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyEntryEntity
import com.devundefined.menulistsample.infrastructure.persistence.cache.CacheKeyRepositoryImpl
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CacheKeyRepositoryTests {
    companion object {
        private const val CACHE_KEY = "CACHE_KEY"
    }

    @Mock
    lateinit var dao: CacheKeyDao

    private lateinit var sut: CacheKeyRepository

    @Before
    fun before() {
        sut = CacheKeyRepositoryImpl(dao)
    }

    @Test
    fun whenFindByCache_ifDaoReturnsNull_shouldReturnNull() {
        whenever(dao.findByCacheKey(CACHE_KEY)).thenReturn(null)

        assertNull(sut.findByCacheKey(CACHE_KEY))
    }

    @Test
    fun whenFindByCache_ifDaoReturnsNotNull_shouldReturnCacheKey_withKeyAndTimeStampThatEqualToDaoValues() {
        val cacheKeyEntity = CacheKeyEntryEntity(CACHE_KEY, 1000)
        whenever(dao.findByCacheKey(eq(CACHE_KEY))).thenReturn(cacheKeyEntity)

        val result = sut.findByCacheKey(CACHE_KEY)

        assertNotNull(result)
        assertEquals(cacheKeyEntity.cacheKey, result!!.cacheKey)
        assertEquals(cacheKeyEntity.timeStamp, result.cacheTimeStamp)
    }

    @Test
    fun whenSave_shouldFirstDeleteFromDaoByCacheKey_andThenSaveToDaoObjectWithSameCacheKeyAndTimeStamp() {
        val cacheKeyEntry = CacheKeyEntry(CACHE_KEY, 100000)

        sut.save(cacheKeyEntry)

        inOrder(dao) {
            verify(dao).removeByCacheKey(eq(cacheKeyEntry.cacheKey))
            verify(dao).insert(argThat { cacheKey == cacheKeyEntry.cacheKey &&
                    timeStamp == cacheKeyEntry.cacheTimeStamp })
        }
    }
}