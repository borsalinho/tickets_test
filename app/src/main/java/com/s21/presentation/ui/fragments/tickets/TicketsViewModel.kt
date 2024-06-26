package com.s21.presentation.ui.tickets

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.GetOffersUseCase
import com.s21.presentation.mappers.toOfferDataView
import com.s21.presentation.mappers.toPopularOfferViewData
import com.s21.presentation.models.OfferViewData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TicketsViewModel(
    application: Application,
    private val getOffersUseCase : GetOffersUseCase
) : ViewModel() {

    private val departurePointFromSharedPreferences = application
        .getSharedPreferences("TicketsViewModelPreferences", Context.MODE_PRIVATE)


    private val _departurePoint = MutableLiveData<String>()
        .apply { value = departurePointFromSharedPreferences
            .getString("DeparturePoint","") ?: ""}
    val departurePoint: LiveData<String> = _departurePoint

    private val _destinationPoint = MutableLiveData<String>("")
    val destinationPoint: LiveData<String> = _destinationPoint

    private val _offers = MutableLiveData<List<OfferViewData>>(emptyList())
    val offers: LiveData<List<OfferViewData>> = _offers

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


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

    fun getOffers(){
        viewModelScope.launch {
            try {
                _offers.value = getOffersUseCase
                    .execute()
                    .map {
                        it.toOfferDataView()
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