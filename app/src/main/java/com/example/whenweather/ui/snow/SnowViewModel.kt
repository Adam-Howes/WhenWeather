package com.example.whenweather.ui.snow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SnowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is snow Fragment"
    }
    val text: LiveData<String> = _text
}