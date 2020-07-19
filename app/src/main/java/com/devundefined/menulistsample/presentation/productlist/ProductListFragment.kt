package com.devundefined.menulistsample.presentation.productlist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.devundefined.menulistsample.R
import com.devundefined.menulistsample.databinding.FragmentProductListBinding
import com.devundefined.menulistsample.domain.models.Category

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    companion object {
        private const val EXTRA_KEY_CATEGORY = "EXTRA_KEY_CATEGORY"
        fun newInstance(category: Category): ProductListFragment = ProductListFragment().apply {
            arguments = bundleOf(EXTRA_KEY_CATEGORY to category.name)
        }
    }

    private lateinit var binding: FragmentProductListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        binding.category.text = arguments?.getString(EXTRA_KEY_CATEGORY) ?: ""
    }
}