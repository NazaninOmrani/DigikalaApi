package com.example.digikala.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    fun saveName(value: String) {
        viewModelScope.launch {
            repository.putUserName("USERNAME", value)
        }
    }

    fun getName(): String? = runBlocking {
        repository.getUserName("USERNAME")
    }

    fun savePass(value: String) {
        viewModelScope.launch {
            repository.putPassword("PASS", value)
        }
    }

    fun getPass(): String? = runBlocking {
        repository.getPassword("PASS")
    }
}