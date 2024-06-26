package com.s21.domain.usecases

import com.s21.domain.models.PopularOffer
import com.s21.domain.models.Ticket
import com.s21.domain.repositoryes.TicketsRepository

class GetTicketsuseCase(private val ticketsRepository: TicketsRepository) {
    suspend fun execute() : List<Ticket>{
        return ticketsRepository.getTickets()
    }
}