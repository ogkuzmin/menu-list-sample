package com.devundefined.menulistsample.domain

import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.infrastructure.Try
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
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

    lateinit var sut: MenuService

    @Before
    fun before() {
        sut = MenuServiceImpl(menuLoadingService, menuRepository)
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
}