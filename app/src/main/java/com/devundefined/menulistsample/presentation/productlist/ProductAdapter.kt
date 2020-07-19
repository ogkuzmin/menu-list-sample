package com.devundefined.menulistsample.presentation.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.databinding.MenuItemBinding
import com.devundefined.menulistsample.domain.models.Product
import java.text.NumberFormat

class ProductAdapter(private val products: Array<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.menu_item, parent, false
        )
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Binder.bind(products[position], holder)
    }
}

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding by lazy { MenuItemBinding.bind(itemView) }
}

object Binder {
    fun bind(product: Product, view: ProductViewHolder) {
        view.binding.run {
            title.text = product.name
            description.text = product.description
            price.text = product.price.let { price ->
                NumberFormat.getCurrencyInstance().apply { currency = price.currency }
                    .format(price.amount)
            }
            showImage(image, product.imageUrl)
        }
    }

    private fun showImage(imageView: ImageView, url: String) {
        if (url.isNotEmpty()) {
            Glide.with(imageView)
                .load(url)
                .placeholder(R.drawable.menu_item_placeholder)
                .into(imageView)
        }
    }
}