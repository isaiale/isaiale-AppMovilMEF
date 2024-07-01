package com.example.mienfermerafav.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _receivedData = MutableLiveData<String>()
    val receivedData: LiveData<String> get() = _receivedData

    fun setReceivedData(data: String) {
        _receivedData.value = data
    }
}