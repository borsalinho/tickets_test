package com.s21.data.network.api

import com.s21.data.network.model.OfferDto
import com.s21.data.network.model.TicketDto
import com.s21.data.network.model.TicketOfferDto
import com.s21.data.network.model.TicketsOffersDto
import retrofit2.http.GET

interface ApiRunMock {

    @GET("v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffersDto() : List<OfferDto>

    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffersDto() : TicketsOffersDto

    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTicketsDto() : List<TicketDto>

}