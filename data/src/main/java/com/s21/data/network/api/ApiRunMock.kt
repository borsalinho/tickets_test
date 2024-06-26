package com.s21.data.network.api


import com.s21.data.network.model.OffersDto
import com.s21.data.network.model.PopularOfferDto
import com.s21.data.network.model.TicketsDto
import com.s21.data.network.model.TicketsOffersDto
import retrofit2.http.GET

interface ApiRunMock {

    @GET("v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffersDto() : OffersDto

    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffersDto() : TicketsOffersDto

    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTicketsDto() : TicketsDto

    // тут можно будет обратится в популярные направления в будущем
    suspend fun getPopularOffers() : List<PopularOfferDto>

}