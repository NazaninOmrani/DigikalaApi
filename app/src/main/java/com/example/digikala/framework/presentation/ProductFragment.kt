package com.example.digikala.framework.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.digikala.R
import com.example.digikala.business.domain.model.Products
import com.example.digikala.business.domain.state.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product.*

@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {

    private val viewModel: ProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStateEvent(StateEvent.GetProductsEvent)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            when (dataState) {
                is DataState.Success<List<Products>> -> {
                    displayProgressBar(false)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}