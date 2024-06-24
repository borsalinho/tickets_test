package com.s21.presentation.models

data class TicketOfferViewData(
    val id : Int,
    val title : String,
    val time_range : List<String>,
    val price : ValueViewData
) : ViewData

data class ValueViewData(
    val value : Int
)