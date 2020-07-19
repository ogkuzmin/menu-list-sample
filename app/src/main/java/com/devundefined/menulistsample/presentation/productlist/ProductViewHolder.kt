package com.devundefined.menulistsample.presentation.productlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devundefined.menulistsample.databinding.MenuItemBinding

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding by lazy {
        MenuItemBinding.bind(
            itemView
        )
    }
}