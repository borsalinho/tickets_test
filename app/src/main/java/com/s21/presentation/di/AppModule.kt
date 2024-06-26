package com.s21.presentation.di

import android.app.Application
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.s21.domain.usecases.GetPopularOffersUseCase
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.domain.usecases.GetTicketsuseCase
import com.s21.presentation.models.ViewData
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.presentation.ui.adapters.ViewDataAdapterFactory
import com.s21.presentation.ui.dialogs.destinationchoise.DestinationChoiseViewModel
import com.s21.presentation.ui.fragments.alltickets.AllTicketsViewModel
import com.s21.presentation.ui.fragments.choiseticket.ChoiseTicketViewModel
import com.s21.presentation.ui.gelegates.PopularOfferViewDataAdapterDelegate
import com.s21.presentation.ui.gelegates.TicketOfferViewDataAdapterDelegate
import com.s21.presentation.ui.gelegates.TicketViewDataAdapterDelegate
import com.s21.presentation.ui.tickets.TicketsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TicketOffer

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class PopularOffer

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Ticket

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
        getPopularOffersUseCase : GetPopularOffersUseCase
    ) : DestinationChoiseViewModel {
        return DestinationChoiseViewModel(
            getPopularOffersUseCase = getPopularOffersUseCase
        )
    }

    @Singleton
    @Provides
    fun provideChoiseTicketViewModel(
        getTicketsOffersUseCase : GetTicketsOffersUseCase
    ) : ChoiseTicketViewModel {
        return ChoiseTicketViewModel(
            getTicketsOffersUseCase = getTicketsOffersUseCase
        )
    }

    @Singleton
    @Provides
    fun ptovideAllTicketsViewModel(
        getTicketsuseCase : GetTicketsuseCase
    ) : AllTicketsViewModel {
        return AllTicketsViewModel(
            getTicketsuseCase = getTicketsuseCase
        )
    }

    @Provides
    fun provideViewDataAdapter(
        @TicketOffer ticketOfferDelegate: AdapterDelegate<List<ViewData>>,
        @PopularOffer popularOfferDelegate: AdapterDelegate<List<ViewData>>,
        @Ticket ticketDelegate : AdapterDelegate<List<ViewData>>
    ) : ViewDataAdapter {
        return ViewDataAdapterFactory.createAdapter(
            delegates = arrayOf(
                ticketOfferDelegate,
                popularOfferDelegate,
                ticketDelegate
            )
        )
    }

    @Provides
    @TicketOffer
    fun provideTicketOfferViewDataAdapterDelegate() : AdapterDelegate<List<ViewData>> {
        return TicketOfferViewDataAdapterDelegate()
    }

    @Provides
    @PopularOffer
    fun providePopularOfferViewDataAdapterDelegate() : AdapterDelegate<List<ViewData>> {
        return PopularOfferViewDataAdapterDelegate()
    }

    @Provides
    @Ticket
    fun provideTicketViewDataAdapterDelegate() : AdapterDelegate<List<ViewData>> {
        return TicketViewDataAdapterDelegate()
    }

}