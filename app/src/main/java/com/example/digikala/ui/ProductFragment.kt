package com.example.digikala.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.digikala.R
import com.example.digikala.business.MainStateEvent
import com.example.digikala.business.MainViewModel
import com.example.digikala.model.Products
import com.example.digikala.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder


@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetProductsEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Products>> -> {
                    displayProgressBar(false)
                    appendProductData(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message.toString())
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String) {
        if (message != null) {
            textView3.text = message
        } else {
            textView3.text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendProductData(products: List<Products>) {
        val sb = StringBuilder()
        for (product in products) {
            sb.append(product.name)
        }
        textView3.text = sb.toString()
    }
}