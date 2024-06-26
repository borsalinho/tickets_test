package com.s21.data.mappers

import com.s21.data.network.model.ArrivalDto
import com.s21.data.network.model.DepartureDto
import com.s21.data.network.model.HandLuggageDto
import com.s21.data.network.model.LuggageDto
import com.s21.data.network.model.TicketDto
import com.s21.domain.models.Arrival
import com.s21.domain.models.Departure
import com.s21.domain.models.HandLuggage
import com.s21.domain.models.Luggage
import com.s21.domain.models.Ticket

fun TicketDto.toTicket() : Ticket {
    return Ticket(
        id = this.id,
        badge = this.badge ?: "",
        price = this.price.toValue(),
        provider_name = this.provider_name,
        company = this.company,
        departure = this.departure.toDeparture(),
        arrival = this.arrival.toArrival(),
        has_transfer = this.has_transfer,
        has_visa_transfer = this.has_visa_transfer,
        luggage = this.luggage?.toLuggage(),
        hand_luggage = this.hand_luggage.toHandLuggage(),
        is_returnable = this.is_returnable,
        is_exchangable = this.is_exchangable
    )
}

fun DepartureDto.toDeparture() : Departure {
    return Departure(
        town = this.town,
        date = this.date,
        airport = this.airport
    )
}

fun ArrivalDto.toArrival() : Arrival {
    return Arrival(
        town = this.town,
        date = this.date,
        airport = this.airport
    )
}

fun LuggageDto.toLuggage() : Luggage {
    return Luggage(
        has_luggage = this.has_luggage,
        price = this.price?.toValue(),

    )
}

fun HandLuggageDto.toHandLuggage() : HandLuggage {
    return HandLuggage(
        has_hand_luggage = this.has_hand_luggage,
        size = this.size ?: ""
    )
}


