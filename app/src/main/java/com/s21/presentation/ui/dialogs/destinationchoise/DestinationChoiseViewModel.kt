package com.s21.presentation.ui.dialogs.destinationchoise

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.presentation.mappers.toTicketOfferViewData
import com.s21.presentation.models.TicketOfferViewData
import kotlinx.coroutines.launch

class DestinationChoiseViewModel(
    private val getTicketsOffersUseCase: GetTicketsOffersUseCase
) : ViewModel() {

    private val _ticketsOffers = MutableLiveData<List<TicketOfferViewData>>(emptyList())

    val ticketsOffers: LiveData<List<TicketOfferViewData>> = _ticketsOffers

    fun getTicketOfferViewData(){
        viewModelScope.launch {

            _ticketsOffers.value = getTicketsOffersUseCase
                .execute()
                .map {
                    it.toTicketOfferViewData()
                }

        }
    }

}