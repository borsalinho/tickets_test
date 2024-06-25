package com.s21.domain.usecases

import com.s21.domain.models.PopularOffer
import com.s21.domain.repositoryes.TicketsRepository

class GetPopularOffersUseCase(private val ticketsRepository: TicketsRepository) {
    suspend fun execute() : List<PopularOffer>{
        return ticketsRepository.getPopularsOffers()
    }
}