package com.example.digikala.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikala.R
import com.example.digikala.business.MainStateEvent
import com.example.digikala.business.MainViewModel
import com.example.digikala.data.model.domain.Products
import com.example.digikala.ui.adapter.ProductAdapter
import com.example.digikala.util.ProductsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.*
/**
 * This class is fragment product for products list
 */
@AndroidEntryPoint
class ProductFragment
    : Fragment(R.layout.fragment_product) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStateEvent(MainStateEvent.GetProductsEvent)
        getProductResponse()
    }

    private fun getProductResponse() {
        viewModel.productsState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is ProductsState.Success<List<Products>> -> {
                    displayProgressBar(false)
                    appendProductData(dataState.data)
                }
                is ProductsState.Error -> {
                    displayProgressBar(false)
                    txt_response.visibility = View.VISIBLE
                    txt_response.text = dataState.exception.message.toString()
                }
                is ProductsState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendProductData(products: List<Products>) {
        recyclerview_product.layoutManager =
            LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        recyclerview_product.adapter = context?.let { ProductAdapter(products, it) }
    }

}