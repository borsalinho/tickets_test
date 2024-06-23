package com.s21.presentation.di

import com.s21.presentation.MainActivity
import com.s21.presentation.ui.dialogs.DestintionChoiseDialogFragment
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
}