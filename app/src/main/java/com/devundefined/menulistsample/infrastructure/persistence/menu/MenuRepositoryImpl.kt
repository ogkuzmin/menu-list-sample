package com.devundefined.menulistsample.infrastructure.persistence.menu

import com.devundefined.menulistsample.domain.MenuRepository
import com.devundefined.menulistsample.domain.models.Category
import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.domain.models.Money
import com.devundefined.menulistsample.domain.models.Product
import java.math.BigDecimal
import java.util.*

class MenuRepositoryImpl(private val dao: ProductDao) : MenuRepository {
    companion object {
        private const val CENTS_FACTOR = 100
    }

    private fun BigDecimal.getAmountForDb(): Double =
        this.multiply(BigDecimal(CENTS_FACTOR)).toDouble()

    private fun Double.getAmountFromDb(): BigDecimal =
        BigDecimal(this).divide(BigDecimal(CENTS_FACTOR))

    private val productToEntity: (Product) -> ProductEntity = { product ->
        with(product) {
            ProductEntity(
                getUniqueId(),
                id,
                name,
                category.name,
                imageUrl,
                description,
                price.amount.getAmountForDb(),
                price.currency.currencyCode
            )
        }
    }

    private fun Product.getUniqueId() = "$id${category.name}"

    private val productEntityToModel: (ProductEntity) -> Product = { entity ->
        with(entity) {
            Product(
                externalId,
                name,
                Category(categoryName),
                imageUrl,
                description,
                Money(priceAmount.getAmountFromDb(), Currency.getInstance(priceCurrencyCode))
            )
        }
    }

    override fun getMenu(): Menu = dao.findAll()
        .map(productEntityToModel)
        .groupBy(Product::category)
        .let(::Menu)

    override fun saveMenu(menu: Menu) {
        dao.clear()
        dao.saveAll(
            menu.entries
                .flatMap { entry -> entry.value }
                .map(productToEntity)
        )
    }
}