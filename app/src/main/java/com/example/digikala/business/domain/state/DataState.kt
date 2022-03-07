package com.example.digikala.business.domain.state

import java.lang.Exception

sealed class DataState<out Response> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Idle : DataState<Nothing>()
}