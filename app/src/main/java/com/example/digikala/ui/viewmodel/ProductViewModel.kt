package com.example.digikala.business

import androidx.lifecycle.*
import com.example.digikala.data.model.domain.Products
import com.example.digikala.data.repository.ProductRepository
import com.example.digikala.util.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _productsState: MutableLiveData<ProductsState<List<Products>>> = MutableLiveData()

    val productsState: LiveData<ProductsState<List<Products>>>
        get() = _productsState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetProductsEvent -> {
                    productRepository.getProduct()
                        .onEach { dataState ->
                            _productsState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // TODO: 3/8/22
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetProductsEvent : MainStateEvent()
    object None : MainStateEvent()
}

