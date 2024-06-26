package com.s21.presentation.di

import com.s21.domain.repositoryes.TicketsRepository
import com.s21.domain.usecases.GetPopularOffersUseCase
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.domain.usecases.GetTicketsuseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetTicketsOffersUseCase(ticketsRepository: TicketsRepository) : GetTicketsOffersUseCase {
        return GetTicketsOffersUseCase(ticketsRepository = ticketsRepository)
    }

    @Singleton
    @Provides
    fun provideGetPopularOffersUseCase(ticketsRepository: TicketsRepository) : GetPopularOffersUseCase {
        return GetPopularOffersUseCase(ticketsRepository = ticketsRepository)
    }

    @Singleton
    @Provides
    fun provideGetTicketsuseCase(ticketsRepository: TicketsRepository) : GetTicketsuseCase {
        return GetTicketsuseCase(ticketsRepository = ticketsRepository)
    }


}