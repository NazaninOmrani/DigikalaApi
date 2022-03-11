package com.example.digikala.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.datastore.UserInfo
import com.example.digikala.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * this viewModel is for get user info from fragment and save info into DataStore by repository
 */
@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val repository: LoginRepository
) : ViewModel() {

    fun saveUserInfo(userInfo: UserInfo) {
        viewModelScope.launch {
            repository.putUserInfo(userInfo)
        }
    }

    fun getUserInfo(): UserInfo? = runBlocking {
        repository.getUserInfo()
    }
}