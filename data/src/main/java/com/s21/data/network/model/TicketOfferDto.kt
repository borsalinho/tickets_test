package com.s21.data.network.model

data class TicketOfferDto(
    val id : Int,
    val title : String,
    val time_range : List<String>,
    val price : Value
)

data class Value(
    var value : Int
)