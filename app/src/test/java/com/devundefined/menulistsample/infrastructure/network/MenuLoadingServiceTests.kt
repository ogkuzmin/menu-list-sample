package com.devundefined.menulistsample.infrastructure.network

import com.devundefined.menulistsample.domain.MenuLoadingService
import com.devundefined.menulistsample.infrastructure.Try
import com.devundefined.menulistsample.infrastructure.network.dto.CategoryWithProducts
import com.devundefined.menulistsample.infrastructure.network.dto.MenuDto
import com.devundefined.menulistsample.infrastructure.network.dto.PriceDto
import com.devundefined.menulistsample.infrastructure.network.dto.ProductDto
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MenuLoadingServiceTests {

    @Mock
    lateinit var api: MenuApi

    private lateinit var sut: MenuLoadingService

    @Before
    fun before() {
        sut = MenuLoadingServiceImpl(api)
    }

    @Test
    fun whenLoadMenu_ifApiThrowsException_shouldReturnFailure() = runBlockingTest {
        whenever(api.getMenu()).thenThrow(IllegalStateException("Boom"))

        assertTrue(sut.loadMenu() is Try.Failure)
    }

    @Test
    fun whenLoadMenu_ifApiReturnsTwoProductDto_withDifferentCategories_shouldReturnMenuWithTwoCategoryEntries_eachWithOneProduct() =
        runBlockingTest {
            val category1 = "category1"
            val category2 = "category2"
            val productDto1 = ProductDto(
                "productId1",
                "productName1",
                "image1",
                "description1",
                PriceDto(1000.0, "EUR")
            )
            val productDto2 = ProductDto(
                "productId2",
                "productName2",
                "image2",
                "description2",
                PriceDto(2000.0, "EUR")
            )
            val menuDto = MenuDto().also { menu ->
                menu.addAll(
                    listOf(
                        CategoryWithProducts(category1, listOf(productDto1)),
                        CategoryWithProducts(category2, listOf(productDto2))
                    )
                )
            }
            whenever(api.getMenu()).thenReturn(menuDto)

            val result = sut.loadMenu()

            assertTrue(result is Try.Success)
            (result as Try.Success).value.also { menu ->
                assertEquals(2, menu.size)
                assertTrue(menu.keys.any { category -> category.name == category1 })
                assertTrue(menu.keys.any { category -> category.name == category2 })
                assertEquals(2, menu.values.size)
                assertEquals(1, menu.values.toList()[0].size)
                assertEquals(1, menu.values.toList()[1].size)
            }
        }
}