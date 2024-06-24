package com.s21.domain.usecases

import com.s21.domain.models.TicketOffer
import com.s21.domain.repositoryes.TicketsRepository

class GetTicketsOffersUseCase(private val ticketsRepository: TicketsRepository) {
    suspend fun execute() : List<TicketOffer>{
        return ticketsRepository.getTicketsOffers()
    }
}