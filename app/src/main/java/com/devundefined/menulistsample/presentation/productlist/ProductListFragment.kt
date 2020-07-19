package com.devundefined.menulistsample.presentation.productlist

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.databinding.FragmentProductListBinding
import com.devundefined.menulistsample.domain.models.Product

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    companion object {
        private const val COLUMNS_COUNT = 2
        private const val EXTRA_KEY_PRODUCT_LIST = "EXTRA_KEY_PRODUCT_LIST"
        fun newInstance(products: Array<Product>): ProductListFragment =
            ProductListFragment().apply {
                arguments = bundleOf(
                    EXTRA_KEY_PRODUCT_LIST to products
                )
            }
    }

    private lateinit var binding: FragmentProductListBinding

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        binding.recycler.run {
            layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LinearLayoutManager(requireContext())
            } else {
                GridLayoutManager(requireContext(), COLUMNS_COUNT)
            }
            adapter = ProductAdapter(
                arguments?.getParcelableArray(EXTRA_KEY_PRODUCT_LIST) as? Array<Product>
                    ?: emptyArray(), ::showProductScreen)
        }
    }

    private fun showProductScreen(product: Product) {

    }
}