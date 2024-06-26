package com.s21.presentation.models


class TicketViewData (
    val id: Int,
    val badge: String? = null,
    val price: ValueViewData,
    val provider_name: String,
    val company: String,
    val departure: DepartureViewData,
    val arrival: ArrivalViewData,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val luggage: LuggageViewData? = null,
    val hand_luggage: HandLuggageViewData,
    val is_returnable: Boolean,
    val is_exchangable: Boolean
) : ViewData

data class DepartureViewData(
    val town: String,
    val date: String,
    val airport: String
)

data class ArrivalViewData(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageViewData(
    val has_luggage: Boolean,
    val price: ValueViewData? = null
)

data class HandLuggageViewData(
    val has_hand_luggage: Boolean,
    val size: String? = null
)