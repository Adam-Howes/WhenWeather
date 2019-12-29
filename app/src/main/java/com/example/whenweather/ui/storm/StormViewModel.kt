package com.example.whenweather.ui.storm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StormViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is storm Fragment"
    }
    val text: LiveData<String> = _text
}