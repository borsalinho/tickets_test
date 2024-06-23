package com.s21.data.network.model


data class TicketDto(
    val id : Int,
    val badge: String,
    val price: Value,
    val provider_name: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val luggage: Luggage,
    val hand_luggage: Hand_luggage,
    val is_returnable: Boolean,
    val is_exchangable: Boolean
)


data class Departure(
    val town: String,
    val date: String,
    val airport: String
)

data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    val has_luggage: Boolean,
    val price: Value
)

data class Hand_luggage(
    val has_hand_luggage: Boolean,
    val size: String
)