package com.s21.presentation.ui.fragments.choiseticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.presentation.mappers.toPopularOfferViewData
import com.s21.presentation.mappers.toTicketOfferViewData
import com.s21.presentation.models.PopularOfferViewData
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.ui.tickets.TicketsViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChoiseTicketViewModel(
    private val getTicketsOffersUseCase : GetTicketsOffersUseCase
) : ViewModel() {


    private val _destinationPoint = MutableLiveData<String>("")
    val destinationPoint: LiveData<String> = _destinationPoint

    private val _departurePoint = MutableLiveData<String>("")
    val departurePoint: LiveData<String> = _departurePoint

    private val _ticketsOffers = MutableLiveData<List<TicketOfferViewData>>(emptyList())
    val ticketsOffers: LiveData<List<TicketOfferViewData>> = _ticketsOffers

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun setDestinationPoint(value: String) {
        _destinationPoint.value = value
    }

    fun setDeparturePoint(value: String) {
        _departurePoint.value = value
    }


    fun getTicketsOffers(){
        viewModelScope.launch {
            try {
                _ticketsOffers.value = getTicketsOffersUseCase
                    .execute()
                    .map {
                        it.toTicketOfferViewData()
                    }
                _error.value = null
            } catch (e: HttpException) {
                _error.value = "Ошибка сети: ${e.message()}"
            } catch (e: IOException) {
                _error.value = "Ошибка ввода-вывода: ${e.message}"
            } catch (e: Exception) {
                _error.value = "Не удалось загрузить данные: ${e.message}"
            }
        }
    }


}