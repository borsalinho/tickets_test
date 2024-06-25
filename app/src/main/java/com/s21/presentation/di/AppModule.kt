package com.s21.presentation.di

import android.app.Application
import android.content.Context
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.s21.domain.usecases.GetPopularOffersUseCase
import com.s21.domain.usecases.GetTicketsOffersUseCase
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.presentation.ui.adapters.OnItemClickListener
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.presentation.ui.adapters.ViewDataAdapterFactory
import com.s21.presentation.ui.dialogs.destinationchoise.DestinationChoiseViewModel
import com.s21.presentation.ui.fragments.choiseticket.ChoiseTicketViewModel
import com.s21.presentation.ui.gelegates.PopularOfferViewDataAdapterDelegate
import com.s21.presentation.ui.gelegates.TicketOfferViewDataAdapterDelegate
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

    @Provides
    fun provideViewDataAdapter(
        @TicketOffer ticketOfferDelegate: AdapterDelegate<List<ViewData>>,
        @PopularOffer popularOfferDelegate: AdapterDelegate<List<ViewData>>
    ) : ViewDataAdapter {
        return ViewDataAdapterFactory.createAdapter(
            delegates = arrayOf(
                ticketOfferDelegate,
                popularOfferDelegate
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

}