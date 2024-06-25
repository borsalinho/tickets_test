package com.s21.presentation.ui.tickets

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicketsViewModel(
    application: Application
) : ViewModel() {

    private val departurePointFromSharedPreferences = application
        .getSharedPreferences("TicketsViewModelPreferences", Context.MODE_PRIVATE)


    private val _departurePoint = MutableLiveData<String>()
        .apply { value = departurePointFromSharedPreferences
            .getString("DeparturePoint","") ?: ""}
    val departurePoint: LiveData<String> = _departurePoint

    private val _destinationPoint = MutableLiveData<String>("")
    val destinationPoint: LiveData<String> = _destinationPoint


    fun setDeparturePoint(departurePoint : String){
        _departurePoint.value = departurePoint
    }

    fun setDestinationPoint(destinationPoint : String){
        _destinationPoint.value = destinationPoint
    }

    fun saveOnSharedPreferences(departurePoint : String){
        departurePointFromSharedPreferences.edit()
            .putString("DeparturePoint", departurePoint)
            .apply()
    }
}