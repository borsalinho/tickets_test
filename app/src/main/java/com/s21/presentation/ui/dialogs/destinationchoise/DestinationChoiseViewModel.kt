package com.s21.presentation.ui.dialogs.destinationchoise

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.GetPopularOffersUseCase
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.presentation.mappers.toPopularOfferViewData

import com.s21.presentation.models.PopularOfferViewData

import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DestinationChoiseViewModel(
    private val getPopularOffersUseCase: GetPopularOffersUseCase
) : ViewModel() {

//    private val _ticketsOffers = MutableLiveData<List<TicketOfferViewData>>(emptyList())
//    val ticketsOffers: LiveData<List<TicketOfferViewData>> = _ticketsOffers

    private val _popularOffers = MutableLiveData<List<PopularOfferViewData>>(emptyList())
    val popularOffers: LiveData<List<PopularOfferViewData>> = _popularOffers

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getPopularOffers(){
        //имитируем обращение в АПИ для получение популярных направлений
        viewModelScope.launch {
            try {
                _popularOffers.value = getPopularOffersUseCase
                    .execute()
                    .map {
                        it.toPopularOfferViewData()
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

//    fun getPopularOffers(){
//        viewModelScope.launch {
//            try {
//                _popularOffers.value = getPopularOffersUseCase
//                    .execute()
//                    .map {
//                        it.toPopularOfferViewData()
//                    }
//                _error.value = null
//            } catch (e: HttpException) {
//                _error.value = "Ошибка сети: ${e.message()}"
//            } catch (e: IOException) {
//                _error.value = "Ошибка ввода-вывода: ${e.message}"
//            } catch (e: Exception) {
//                _error.value = "Не удалось загрузить данные: ${e.message}"
//            }
//        }
//    }

}