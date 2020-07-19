package com.devundefined.menulistsample.presentation.productlist

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.domain.models.Product
import java.text.NumberFormat

object Binder {
    fun bind(product: Product, view: ProductViewHolder, listener: (Product) -> Unit) {
        view.itemView.setOnClickListener { listener(product) }
        view.binding.run {
            title.text = product.name
            description.text = product.description
            price.text = product.price.toString()
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