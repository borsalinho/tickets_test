package com.s21.presentation.app

import android.app.Application
import com.s21.presentation.di.AppComponent
import com.s21.presentation.di.AppModule
import com.s21.presentation.di.DaggerAppComponent
import com.s21.presentation.di.DataModule
import com.s21.presentation.di.DomainModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(application = this))
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .build()
    }
}