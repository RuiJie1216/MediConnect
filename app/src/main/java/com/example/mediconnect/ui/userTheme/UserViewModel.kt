package com.example.mediconnect.ui.userTheme

import androidx.lifecycle.ViewModel
import com.example.mediconnect.data.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel: ViewModel() {
    private val _userUiState = MutableStateFlow(UserUiState())
    val userUiState: StateFlow<UserUiState> = _userUiState.asStateFlow()

    fun setID(ic: String) {
        _userUiState.update { current ->
            current.copy(
                ic = ic
            )
        }
    }

    fun setPwd(pwd: String) {
        _userUiState.update { current ->
            current.copy(
                pwd = pwd
            )
        }
    }


}