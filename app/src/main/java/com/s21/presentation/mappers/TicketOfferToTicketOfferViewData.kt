package com.s21.presentation.mappers

import com.s21.domain.models.TicketOffer
import com.s21.domain.models.Value
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ValueViewData

fun TicketOffer.toTicketOfferViewData() : TicketOfferViewData {
    return TicketOfferViewData(
        id = this.id,
        title = this.title,
        time_range = this.time_range,
        price = this.price.toValueViewData()
    )
}

fun Value.toValueViewData() : ValueViewData {
    return ValueViewData(
        value = this.value
    )
}