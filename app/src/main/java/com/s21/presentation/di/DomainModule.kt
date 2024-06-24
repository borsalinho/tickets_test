package com.s21.presentation.di

import com.s21.domain.repositoryes.TicketsRepository
import com.s21.domain.usecases.GetTicketsOffersUseCase
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
}