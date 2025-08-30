package com.example.mediconnect.ui.userTheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _personalInfo = MutableStateFlow(Personal_Info())
    val personalInfo: StateFlow<Personal_Info> = _personalInfo

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun loginUser(user: Personal_Info) {
        viewModelScope.launch {
            _personalInfo.value = user
            _isLoggedIn.value = true
        }
    }

    fun setUserData(user: Personal_Info) {
        viewModelScope.launch {
            _personalInfo.value = user
            _isLoggedIn.value = true
        }
    }

    fun logout() {
        viewModelScope.launch {
            _personalInfo.value = Personal_Info()
            _isLoggedIn.value = false
        }
    }

    fun loadUserData(userId: String) {
        viewModelScope.launch {
            val userData = getUserDataFromStorage(userId)
            _personalInfo.value = userData
        }
    }

    private suspend fun getUserDataFromStorage(userId: String): Personal_Info {
        return Personal_Info(
            userId = userId,
            name = "",
            age = "",
            dob = "",
            gender = "",
            email = "",
            address = "",
            phone = "",
            medicalHistory = "",
            weight = "",
            height = ""
        )
    }
}