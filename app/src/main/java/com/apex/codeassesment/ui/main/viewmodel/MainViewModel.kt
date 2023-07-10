package com.apex.codeassesment.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.apex.codeassesment.data.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun getUsersData() = userRepository.getUsers()

    fun getSavedUsers() = userRepository.getSavedUser()

    fun getUser() = userRepository.getUser(true)
}