package com.s21.domain.repositoryes

import com.s21.domain.models.Offer
import com.s21.domain.models.PopularOffer
import com.s21.domain.models.Ticket
import com.s21.domain.models.TicketOffer

interface TicketsRepository {
    suspend fun getTicketsOffers() : List<TicketOffer>
    suspend fun getTickets() : List<Ticket>
    suspend fun getOffers() : List<Offer>
    suspend fun getPopularsOffers() : List<PopularOffer>
}