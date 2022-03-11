package com.example.digikala.util

import java.lang.Exception

/**
 * this class is for response state in repository
 */
sealed class ProductsState<out R> {

    data class Success<out T>(val data: T) : ProductsState<T>()
    data class Error(val exception: Exception) : ProductsState<Nothing>()
    object Loading : ProductsState<Nothing>()
}