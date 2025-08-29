package com.example.mediconnect.ui.doctorTheme

import androidx.lifecycle.ViewModel
import com.example.mediconnect.data.DocUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DocViewModel: ViewModel() {
    private val _docUiState = MutableStateFlow(DocUiState())
    val docUiState: StateFlow<DocUiState> = _docUiState.asStateFlow()

    fun setID(id: String) {
        _docUiState.update { current ->
            current.copy(
                id = id
            )
        }
    }

    fun setPwd(pwd: String) {
        _docUiState.update { current ->
            current.copy(
                pwd = pwd
            )
        }
    }




}