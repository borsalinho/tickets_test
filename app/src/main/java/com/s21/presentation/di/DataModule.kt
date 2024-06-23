package com.s21.presentation.di

import com.s21.data.implementations.TicketsRepositoryImpl
import com.s21.data.network.api.ApiRunMock
import com.s21.domain.repositoryes.TicketsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    private val baseUrl = "https://run.mocky.io/"

    @Singleton
    @Provides
    fun provideTicketsRepositoryImpl(
        apiRunMock : ApiRunMock
    ) : TicketsRepository {
        return TicketsRepositoryImpl(
            apiRunMock = apiRunMock
        )
    }


    @Singleton
    @Provides
    fun provideApiRunMock() : ApiRunMock {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRunMock::class.java)
    }
}