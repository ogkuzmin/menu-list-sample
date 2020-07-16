package com.devundefined.menulistsample.domain

import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.infrastructure.cache.CacheValidator
import com.devundefined.menulistsample.infrastructure.Try
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MenuServiceTests {
    @Mock
    lateinit var menuLoadingService: MenuLoadingService

    @Mock
    lateinit var menuRepository: MenuRepository

    @Mock
    lateinit var cacheValidator: CacheValidator

    lateinit var sut: MenuService

    @Before
    fun before() {
        sut = MenuServiceImpl(menuLoadingService, menuRepository, cacheValidator)
    }

    @Test
    fun whenGetMenu_ifLoadingServiceReturnedFailure_shouldReturnFailure() {
        whenever(menuLoadingService.loadMenu()).thenReturn(Try.Failure(IllegalArgumentException("Test")))

        val result = sut.getMenu()

        assertTrue(result is Try.Failure)
    }

    @Test
    fun whenGetMenu_ifLoadingServiceReturnedSuccessWithMenu_shouldReturnSuccessWithSameMenu() {
        val menu = Menu(mapOf())
        whenever(menuLoadingService.loadMenu()).thenReturn(Try.Success(menu))

        val result = sut.getMenu()

        assertTrue(result is Try.Success)
        assertEquals(menu, (result as Try.Success).value)
    }

    @Test
    fun whenGetMenu_ifLoadingServiceReturnedSuccessWithMenu_shouldSaveMenuToRepo() {
        val menu = Menu(mapOf())
        whenever(menuLoadingService.loadMenu()).thenReturn(Try.Success(menu))

        sut.getMenu()

        verify(menuRepository).saveMenu(eq(menu))
    }

    @Test
    fun whenGetMenu_ifLoadingServiceReturnedFailure_shouldNeverSaveMenuToRepo() {
        whenever(menuLoadingService.loadMenu()).thenReturn(Try.Failure(IllegalArgumentException("Test")))

        sut.getMenu()

        verify(menuRepository, never()).saveMenu(any())
    }

    @Test
    fun whenGetMenu_ifCacheIsValid_shouldGetMenuFromRepository_andNeverLoadMenuToLoadingService() {
        val cachedMenu = Menu(mapOf())
        whenever(menuRepository.findMenu()).thenReturn(cachedMenu)
        whenever(cacheValidator.isValid(any(), any())).thenReturn(true)

        val result = sut.getMenu()

        assertTrue(result is Try.Success)
        assertEquals(cachedMenu, (result as Try.Success).value)
        verify(menuLoadingService, never()).loadMenu()
    }

    @Test
    fun whenGetMenu_ifCacheIsNotValid_andLoadingServiceReturnedSuccessWithMenu_shouldInvalidateCache() {
        val menu = Menu(mapOf())
        whenever(cacheValidator.isValid(any(), any())).thenReturn(false)
        whenever(menuLoadingService.loadMenu()).thenReturn(Try.Success(menu))

        sut.getMenu()

        verify(cacheValidator).invalidateCache(any())
    }
}