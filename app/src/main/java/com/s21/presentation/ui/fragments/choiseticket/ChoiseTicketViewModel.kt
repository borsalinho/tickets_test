package com.s21.presentation.ui.fragments.choiseticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.presentation.ui.tickets.TicketsViewModel
import javax.inject.Inject

class ChoiseTicketViewModel(
    private val getTicketsOffersUseCase : GetTicketsOffersUseCase
) : ViewModel() {


    private val _destinationPoint = MutableLiveData<String>("")
    val destinationPoint: LiveData<String> = _destinationPoint

    private val _departurePoint = MutableLiveData<String>("")
    val departurePoint: LiveData<String> = _departurePoint

    fun setDestinationPoint(value: String) {
        _destinationPoint.value = value
    }

    fun setDeparturePoint(value: String) {
        _departurePoint.value = value
    }


}