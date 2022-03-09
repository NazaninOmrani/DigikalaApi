package com.example.digikala.business

import androidx.lifecycle.*
import com.example.digikala.data.domain.Products
import com.example.digikala.data.repository.ProdutRepository
import com.example.digikala.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val produtRepository: ProdutRepository,
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Products>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Products>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetProductsEvent -> {
                    produtRepository.getProduct()
                        .onEach { dataState ->
                            _dataState.value = dataState
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

