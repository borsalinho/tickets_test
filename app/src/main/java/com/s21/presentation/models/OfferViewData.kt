package com.s21.presentation.models

data class OfferViewData(
    val id: Int,
    val title: String,
    val town: String,
    val price: ValueViewData
) : ViewData
