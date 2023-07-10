package com.apex.codeassesment.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apex.codeassesment.data.UserRepository
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    var users = MutableLiveData<List<User>?>()
    var user = MutableLiveData<User?>()
    fun getUsersData() {
        viewModelScope.launch(Dispatchers.IO) {
            when(val apiCall= userRepository.makeApiToGetRandomUsers()){
                is Response.Failure -> {}
                Response.Loading -> {}
                is Response.Success -> {
                    users.value = apiCall.data
                }
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            when(val apiCall= userRepository.makeApiToGetUser()){
                is Response.Failure -> {}
                Response.Loading -> {}
                is Response.Success -> {
               user.value = (apiCall.data)
                }
            }
        }
    }

//    fun getSavedUsers() = userRepository.getSavedUser()

 //   fun getUser() = userRepository.getUser(true)
}