package com.s21.data.mappers

import com.s21.data.network.model.OfferDto
import com.s21.domain.models.Offer


fun OfferDto.toOffer() : Offer {
    return Offer(
        id = this.id,
        title = this.title,
        town = this.town,
        price = this.price.toValue()
    )
}

fun com.s21.data.network.model.Value.toValue() : com.s21.domain.models.Value {
    return com.s21.domain.models.Value(
        value = this.value
    )
}