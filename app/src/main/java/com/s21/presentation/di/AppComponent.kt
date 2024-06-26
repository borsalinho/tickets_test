package com.s21.presentation.di

import com.s21.presentation.MainActivity
import com.s21.presentation.ui.dialogs.destinationchoise.DestintionChoiseDialogFragment
import com.s21.presentation.ui.fragments.alltickets.AllTicketsFragment
import com.s21.presentation.ui.fragments.choiseticket.ChoiseTicketFragment
import com.s21.presentation.ui.fragments.choiseticket.ChoiseTicketViewModel
import com.s21.presentation.ui.tickets.TicketsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DataModule::class,
    DomainModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(ticketsFragment: TicketsFragment)
    fun inject(destintionChoiseDialogFragment : DestintionChoiseDialogFragment)
    fun inject(choiseTicketFragment : ChoiseTicketFragment)
    fun inject(allTicketsFragment : AllTicketsFragment)
}