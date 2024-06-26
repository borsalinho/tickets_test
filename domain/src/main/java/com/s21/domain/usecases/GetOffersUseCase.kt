package com.s21.domain.usecases

import com.s21.domain.models.Offer

import com.s21.domain.repositoryes.TicketsRepository

class GetOffersUseCase(private val ticketsRepository: TicketsRepository) {
    suspend fun execute() : List<Offer>{
        return ticketsRepository.getOffers()
    }
}