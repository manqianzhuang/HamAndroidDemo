package com.superman.animate.example.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    fun launch(block: suspend ()-> Unit, error: (t: java.lang.Exception)-> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                error(e)
            }
        }
    }

}