package com.s21.data.network.model

data class TicketsDto(
    val tickets : List<TicketDto>
)

data class TicketDto(
    val id : Int,
    val badge: String? = null,
    val price: ValueDto,
    val provider_name: String,
    val company: String,
    val departure: DepartureDto,
    val arrival: ArrivalDto,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val luggage: LuggageDto? = null,
    val hand_luggage: HandLuggageDto,
    val is_returnable: Boolean,
    val is_exchangable: Boolean
)


data class DepartureDto(
    val town: String,
    val date: String,
    val airport: String
)

data class ArrivalDto(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageDto(
    val has_luggage: Boolean,
    val price: ValueDto? = null
)

data class HandLuggageDto(
    val has_hand_luggage: Boolean,
    val size: String? = null
)