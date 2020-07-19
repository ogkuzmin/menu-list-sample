package com.devundefined.menulistsample.presentation.productcard

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.databinding.FragmentProductCardBinding
import com.devundefined.menulistsample.domain.models.Product

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {
    companion object {
        private const val EXTRA_KEY_PRODUCT = "EXTRA_KEY_PRODUCT"
        fun newInstance(product: Product) = ProductCardFragment().apply {
            arguments = bundleOf(EXTRA_KEY_PRODUCT to product)
        }
    }

    private lateinit var binding: FragmentProductCardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductCardBinding.bind(view)
        arguments?.getParcelable<Product>(EXTRA_KEY_PRODUCT)?.let { product ->
            binding.run {
                price.text = product.price.toString()
                title.text = product.name
                subtitle.text = product.description
                showImage(image, product.imageUrl)
            }
        }
    }

    private fun showImage(imageView: ImageView, url: String) {
        if (url.isNotEmpty()) {
            val color = ContextCompat.getColor(requireContext(), R.color.greyBackground)
            val drawable =
                ResourcesCompat.getDrawable(resources, R.drawable.menu_item_placeholder, null)
                    ?.apply { setTint(color) }
            Glide.with(imageView)
                .load(url)
                .placeholder(drawable)
                .into(imageView)
        }
    }
}