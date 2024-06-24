package com.s21.data.implementations

import com.s21.data.mappers.toOffer
import com.s21.data.mappers.toTicket
import com.s21.data.mappers.toTicketOffer
import com.s21.data.network.api.ApiRunMock
import com.s21.domain.models.Offer
import com.s21.domain.models.Ticket
import com.s21.domain.models.TicketOffer
import com.s21.domain.repositoryes.TicketsRepository

class TicketsRepositoryImpl(
    var apiRunMock: ApiRunMock
) : TicketsRepository {

    override suspend fun getOffers(): List<Offer> {
        return apiRunMock.getOffersDto().map { it.toOffer() }
    }

    override suspend fun getTickets(): List<Ticket> {
        return apiRunMock.getTicketsDto().map { it.toTicket() }
    }

    override suspend fun getTicketsOffers(): List<TicketOffer> {

        return apiRunMock.getTicketsOffersDto().tickets_offers.map { it.toTicketOffer() }
    }
}