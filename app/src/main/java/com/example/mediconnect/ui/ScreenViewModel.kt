package com.example.mediconnect.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenViewModel(): ViewModel() {

    private val _step = MutableStateFlow(1)
    val step: StateFlow<Int> = _step.asStateFlow()


    fun setStep(step: Int) {
        _step.value = step
    }

    fun reset() {
        _step.value = 1
    }


}