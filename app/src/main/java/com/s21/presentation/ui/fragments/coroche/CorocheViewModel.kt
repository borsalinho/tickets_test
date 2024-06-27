package com.s21.presentation.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CorocheViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Заглушка для КОРОЧЕ"
    }
    val text: LiveData<String> = _text
}