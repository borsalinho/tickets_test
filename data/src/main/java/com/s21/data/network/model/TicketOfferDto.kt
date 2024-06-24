package com.s21.data.network.model

data class TicketsOffersDto(
    val tickets_offers : List<TicketOfferDto>
)
data class TicketOfferDto(
    val id : Int,
    val title : String,
    val time_range : List<String>,
    val price : ValueDto
)

data class ValueDto(
    var value : Int
)