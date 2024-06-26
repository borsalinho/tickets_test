package com.s21.presentation.mappers

import com.s21.domain.models.Arrival
import com.s21.domain.models.Departure
import com.s21.domain.models.HandLuggage
import com.s21.domain.models.Luggage
import com.s21.domain.models.Ticket
import com.s21.presentation.models.ArrivalViewData
import com.s21.presentation.models.DepartureViewData
import com.s21.presentation.models.HandLuggageViewData
import com.s21.presentation.models.LuggageViewData
import com.s21.presentation.models.TicketViewData


fun Ticket.toTicketViewData() : TicketViewData {
    return TicketViewData(
        id = this.id,
        badge = this.badge,
        price = this.price.toValueViewData(),
        provider_name = this.provider_name,
        company = this.company,
        departure = this.departure.toDepartureViewData(),
        arrival = this.arrival.toArrivalViewData(),
        has_transfer = this.has_transfer,
        has_visa_transfer = this.has_visa_transfer,
        luggage = this.luggage?.toLuggageViewData(),
        hand_luggage = this.hand_luggage.toHandLuggageViewData(),
        is_returnable = this.is_returnable,
        is_exchangable = this.is_exchangable
    )
}

fun Departure.toDepartureViewData() : DepartureViewData {
    return DepartureViewData(
        town = this.town,
        date = this.date,
        airport = this.airport
    )
}

fun Arrival.toArrivalViewData() : ArrivalViewData {
    return ArrivalViewData(
        town = this.town,
        date = this.date,
        airport = this.airport
    )
}

fun Luggage.toLuggageViewData() : LuggageViewData {
    return LuggageViewData(
        has_luggage = this.has_luggage,
        price = this.price?.toValueViewData()
    )
}

fun HandLuggage.toHandLuggageViewData() : HandLuggageViewData {
    return HandLuggageViewData(
        has_hand_luggage = this.has_hand_luggage,
        size = this.size
    )
}
