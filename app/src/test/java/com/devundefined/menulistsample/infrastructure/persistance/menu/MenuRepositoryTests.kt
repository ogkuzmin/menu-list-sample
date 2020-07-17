package com.devundefined.menulistsample.infrastructure.persistance.menu

import com.devundefined.menulistsample.domain.MenuRepository
import com.devundefined.menulistsample.domain.models.Category
import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.domain.models.Money
import com.devundefined.menulistsample.domain.models.Product
import com.devundefined.menulistsample.infrastructure.persistence.menu.MenuRepositoryImpl
import com.devundefined.menulistsample.infrastructure.persistence.menu.ProductDao
import com.devundefined.menulistsample.infrastructure.persistence.menu.ProductEntity
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MenuRepositoryTests {

    @Mock
    lateinit var dao: ProductDao

    lateinit var sut: MenuRepository

    @Before
    fun before() {
        sut = MenuRepositoryImpl(dao)
    }

    @Test
    fun whenGetMenu_ifDaoContainsEmptyCollection_shouldReturnEmptyMenu() {
        whenever(dao.findAll()).thenReturn(listOf())

        assertTrue(sut.getMenu().isEmpty())
    }

    @Test
    fun whenGetMenu_ifDaoContainsTwoProducts_withDifferentCategories_shouldReturnMenu_withTwoEntries_eachWithOneElement() {
        val firstCategory = Category("category1")
        val secondCategory = Category("category2")
        val productEntityFirst = ProductEntity(
            "id1",
            "externalId1",
            "product1",
            firstCategory.name,
            "imageUrl1",
            "description1",
            100000.0,
            "EUR"
        )
        val productEntitySecond = ProductEntity(
            "id2",
            "externalId2",
            "product2",
            secondCategory.name,
            "imageUrl2",
            "description2",
            200000.0,
            "EUR"
        )
        whenever(dao.findAll()).thenReturn(listOf(productEntityFirst, productEntitySecond))

        val menu = sut.getMenu()

        assertTrue(menu.size == 2)
        assertTrue(menu[firstCategory]?.size == 1)
        assertTrue(menu[firstCategory]?.first()?.name == productEntityFirst.name)
        assertTrue(menu[secondCategory]?.size == 1)
        assertTrue(menu[secondCategory]?.first()?.name == productEntitySecond.name)
    }

    @Test
    fun whenSaveMenu_ifMenuWithOneProduct_shouldFirstlyCleareDatabase_andThenSaveProductListToRepo_withSizeOne() {
        val category = Category("some")
        val price = Money(BigDecimal(1000), Currency.getInstance("EUR"))
        val product =
            Product("productId", "productName", category, "imageUrl", "description", price)
        val menu = Menu(mapOf(category to listOf(product)))

        sut.saveMenu(menu)

        inOrder(dao) {
            verify(dao).clear()
            verify(dao).saveAll(argThat {
                size == 1 &&
                        first().run {
                            id == product.id &&
                                    name == product.name &&
                                    priceCurrencyCode == price.currency.currencyCode &&
                                    imageUrl == product.imageUrl
                        }
            })
        }
    }
}