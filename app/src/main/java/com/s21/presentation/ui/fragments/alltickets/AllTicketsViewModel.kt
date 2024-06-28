package com.s21.presentation.ui.fragments.alltickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.GetTicketsuseCase
import com.s21.presentation.mappers.toPopularOfferViewData
import com.s21.presentation.mappers.toTicketViewData

import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.TicketViewData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class AllTicketsViewModel(
    private val getTicketsuseCase : GetTicketsuseCase
) : ViewModel() {


    private val _destinationPoint = MutableLiveData<String>("")
    val destinationPoint: LiveData<String> = _destinationPoint
    private val _departurePoint = MutableLiveData<String>("")
    val departurePoint: LiveData<String> = _departurePoint

    private val _departDest = MediatorLiveData<String>().apply {
        addSource(_departurePoint) { departureValue ->
            val newValue = departureValue.plus(" - ").plus(_destinationPoint.value ?: "")
            this.value = newValue
        }
        addSource(_destinationPoint) { destinationValue ->
            val newValue = _departurePoint.value.plus(" - ").plus(destinationValue)
            this.value = newValue
        }
    }
    val departDest: LiveData<String> = _departDest

    private val _tickets = MutableLiveData<List<TicketViewData>>(emptyList())
    val tickets: LiveData<List<TicketViewData>> = _tickets

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun setDestinationPoint(value: String) {
        _destinationPoint.value = value
    }

    fun setDeparturePoint(value: String) {
        _departurePoint.value = value
    }

    // у нас АПИ не может фильтровать поэтому опять будем имитировать отправку запроса с нужными городами

    fun getTickets(){
        viewModelScope.launch {
            try {
                _tickets.value = getTicketsuseCase
                    .execute()
                    .map {
                        it.toTicketViewData()
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