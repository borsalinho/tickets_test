package com.s21.data.mappers

import com.s21.data.network.model.TicketOfferDto
import com.s21.domain.models.TicketOffer
import com.s21.domain.models.Value

fun TicketOfferDto.toTicketOffer() : TicketOffer {
    return TicketOffer(
        id = this.id,
        title = this.title,
        time_range = this.time_range,
        price = this.price.toValue()
    )
}