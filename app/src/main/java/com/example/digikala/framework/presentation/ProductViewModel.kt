package com.example.digikala.framework.presentation

import androidx.lifecycle.*
import com.example.digikala.business.domain.model.Products
import com.example.digikala.business.domain.state.DataState
import com.example.digikala.business.intractors.GetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel
@Inject
constructor(
    private val getProduct: GetProduct,
) : ViewModel() {

    private val dataStates: MutableLiveData<DataState<List<Products>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Products>>>
        get() = dataStates


    fun setStateEvent(stateEvent: StateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is StateEvent.GetProductsEvent -> {
                    getProduct.execute()
                        .onEach { dataState ->
                            dataStates.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class StateEvent {

    object GetProductsEvent : StateEvent()

    object NothingEvent : StateEvent()
}
