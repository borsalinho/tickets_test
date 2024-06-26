package com.s21.data.implementations

import android.util.Log
import com.s21.data.mappers.toOffer
import com.s21.data.mappers.toPopularOffer
import com.s21.data.mappers.toTicket
import com.s21.data.mappers.toTicketOffer
import com.s21.data.network.api.ApiRunMock
import com.s21.data.network.model.PopularOfferDto
import com.s21.domain.models.Offer
import com.s21.domain.models.PopularOffer
import com.s21.domain.models.Ticket
import com.s21.domain.models.TicketOffer
import com.s21.domain.repositoryes.TicketsRepository

class TicketsRepositoryImpl(
    var apiRunMock: ApiRunMock
) : TicketsRepository {

    override suspend fun getOffers() : List<Offer> {
        return apiRunMock.getOffersDto().map { it.toOffer() }
    }

//    override suspend fun getTickets() : List<Ticket> {
//        Log.d("MyTag", "пытаюсь получить из сети")
//        val res = apiRunMock.getTicketsDto().tickets.map { it.toTicket() }
//        Log.d("MyTag", "пришло из сети")
//        Log.d("MyTag", res.toString())
//        return res
//    }
    override suspend fun getTickets(): List<Ticket> {
        try {
            Log.d("MyTag", "пытаюсь получить из сети")
            val res = apiRunMock.getTicketsDto()
            Log.d("MyTag", "пришло из сети")
            Log.d("MyTag", res.toString())
            val res2 = res.tickets.map { it.toTicket() }
            Log.d("MyTag", "после маппа")
            Log.d("MyTag", res2.toString())
            return res2
        } catch (e: Exception) {
            Log.e("MyTag", "Ошибка получения данных из сети: ${e.message}")
            return emptyList() // или другое поведение при ошибке
        }
    }

    override suspend fun getTicketsOffers() : List<TicketOffer> {
        return apiRunMock.getTicketsOffersDto().tickets_offers.map { it.toTicketOffer() }
    }

    // имитируем обращение в сеть
    override suspend fun getPopularsOffers() : List<PopularOffer> {
        return listOf(
            PopularOfferDto("Стамбул", "Популярное направление").toPopularOffer(),
            PopularOfferDto("Сочи", "Популярное направление").toPopularOffer(),
            PopularOfferDto("Пхукет", "Типа, популярное направление" ).toPopularOffer(),
            PopularOfferDto("Магадан", "НЕ популярное направление" ).toPopularOffer()
        )
    }
}