package com.s21.domain.models

data class TicketOffer(
    val id : Int,
    val title : String,
    val time_range : List<String>,
    val price : Value
)

data class Value(
    val value : Int
)