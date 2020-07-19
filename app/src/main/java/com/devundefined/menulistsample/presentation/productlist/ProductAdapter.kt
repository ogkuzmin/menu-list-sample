package com.devundefined.menulistsample.presentation.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.domain.models.Product

class ProductAdapter(
    private val products: Array<Product>,
    private val clickListener: (Product) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.menu_item, parent, false
        )
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Binder.bind(products[position], holder, clickListener)
    }
}

