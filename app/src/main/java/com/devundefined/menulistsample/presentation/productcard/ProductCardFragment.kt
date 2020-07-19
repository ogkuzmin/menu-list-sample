package com.devundefined.menulistsample.presentation.productcard

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.databinding.FragmentProductCardBinding
import com.devundefined.menulistsample.domain.models.Product
import com.devundefined.menulistsample.presentation.navigation.RouterHolder

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
                closeIcon.setOnClickListener { back() }
            }
        }
        view.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                back()
                true
            } else {
                false
            }
        }
    }

    private fun back() {
        RouterHolder.INSTANCE?.router?.back()
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
                .error(drawable)
                .fitCenter()
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e("ProductCard", "image loading failed")
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageView.imageTintList = null
                        return false
                    }
                })
                .into(imageView)
        }
    }
}