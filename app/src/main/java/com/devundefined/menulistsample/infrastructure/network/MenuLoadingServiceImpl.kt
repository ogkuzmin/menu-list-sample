package com.devundefined.menulistsample.infrastructure.network

import com.devundefined.menulistsample.domain.MenuLoadingService
import com.devundefined.menulistsample.domain.models.Category
import com.devundefined.menulistsample.domain.models.Menu
import com.devundefined.menulistsample.domain.models.Money
import com.devundefined.menulistsample.domain.models.Product
import com.devundefined.menulistsample.infrastructure.Try
import com.devundefined.menulistsample.infrastructure.network.dto.MenuDto
import com.devundefined.menulistsample.infrastructure.network.dto.PriceDto
import com.devundefined.menulistsample.infrastructure.network.dto.ProductDto
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal
import java.util.*

class MenuLoadingServiceImpl(private val menuApi: MenuApi) : MenuLoadingService {

    override fun loadMenu(): Try<Menu> = runBlocking {
        Try.tryBlocking {
            menuApi.getMenu().let(::dtoToModel)
        }
    }

    private fun dtoToModel(dto: MenuDto): Menu {
        return dto.map { categoryWithProducts ->
            Category(categoryWithProducts.categoryName) to categoryWithProducts.products
        }
            .map { pair -> pair.first to pair.second.map { dto -> dto.toModel(pair.first) } }
            .toMap()
            .let(::Menu)
    }

    private fun ProductDto.toModel(category: Category): Product = Product(
        id,
        name,
        category,
        "${MenuApi.BASE_URL}$imageUrlPath",
        description,
        price.toModel()
    )

    private fun PriceDto.toModel(): Money =
        Money(BigDecimal(amount), Currency.getInstance(currencyCode))
}