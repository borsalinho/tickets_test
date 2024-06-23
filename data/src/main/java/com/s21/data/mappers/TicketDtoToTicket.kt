package com.s21.data.mappers

import com.s21.data.network.model.TicketDto
import com.s21.data.network.model.Value
import com.s21.domain.models.Ticket

fun TicketDto.toTicket() : Ticket {
    return Ticket(
        id = this.id,
        badge = this.badge,
        price = this.price.toValue(),
        provider_name = this.provider_name,
        company = this.company,
        departure = this.departure.toDeparture(),
        arrival = this.arrival.toArrival(),
        has_transfer = this.has_transfer,
        has_visa_transfer = this.has_visa_transfer,
        luggage = this.luggage.toLuggage(),
        hand_luggage = this.hand_luggage.toHandLuggage(),
        is_returnable = this.is_returnable,
        is_exchangable = this.is_exchangable
    )
}

fun com.s21.data.network.model.Departure.toDeparture() : com.s21.domain.models.Departure {
    return com.s21.domain.models.Departure(
        town = this.town,
        date = this.date,
        airport = this.airport
    )
}

fun com.s21.data.network.model.Arrival.toArrival() : com.s21.domain.models.Arrival {
    return com.s21.domain.models.Arrival(
        town = this.town,
        date = this.date,
        airport = this.airport
    )
}

fun com.s21.data.network.model.Luggage.toLuggage() : com.s21.domain.models.Luggage {
    return com.s21.domain.models.Luggage(
        has_luggage = this.has_luggage,
        price = this.price.toValue(),

    )
}

fun com.s21.data.network.model.Hand_luggage.toHandLuggage() : com.s21.domain.models.Hand_luggage {
    return com.s21.domain.models.Hand_luggage(
        has_hand_luggage = this.has_hand_luggage,
        size = this.size
    )
}


