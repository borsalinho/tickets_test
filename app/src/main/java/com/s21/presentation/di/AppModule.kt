package com.s21.presentation.di

import android.app.Application
import android.content.Context
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.presentation.ui.adapters.ViewDataAdapterFactory
import com.s21.presentation.ui.dialogs.destinationchoise.DestinationChoiseViewModel
import com.s21.presentation.ui.gelegates.TicketOfferViewDataAdapterDelegate
import com.s21.presentation.ui.tickets.TicketsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun providerContext() : Application {
        return application
    }

    @Singleton
    @Provides
    fun provideTicketsViewModel(
        application : Application
    ) : TicketsViewModel {
        return TicketsViewModel(
            application = application
        )
    }

    @Singleton
    @Provides
    fun provideDestinationChoiseViewModel(
        getTicketsOffersUseCase: GetTicketsOffersUseCase
    ) : DestinationChoiseViewModel {
        return DestinationChoiseViewModel(
            getTicketsOffersUseCase = getTicketsOffersUseCase
        )
    }

    @Provides
    fun provideViewDataAdapter(
        ticketOfferDelegate: AdapterDelegate<List<ViewData>>,
    ) : ViewDataAdapter {
        return ViewDataAdapterFactory.createAdapter(
            ticketOfferDelegate
        )
    }

    @Provides
    fun provideTicketOfferViewDataAdapterDelegate() : AdapterDelegate<List<ViewData>> {
        return TicketOfferViewDataAdapterDelegate()
    }
}