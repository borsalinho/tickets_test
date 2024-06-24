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
import retrofit2.HttpException
import java.io.IOException

class DestinationChoiseViewModel(
    private val getTicketsOffersUseCase: GetTicketsOffersUseCase
) : ViewModel() {

    private val _ticketsOffers = MutableLiveData<List<TicketOfferViewData>>(emptyList())
    val ticketsOffers: LiveData<List<TicketOfferViewData>> = _ticketsOffers

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getTicketOfferViewData(){
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