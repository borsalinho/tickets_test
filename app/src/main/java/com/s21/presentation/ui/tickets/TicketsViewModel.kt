package com.s21.presentation.ui.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicketsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is TICKETS Fragment"
    }
    val text: LiveData<String> = _text
}