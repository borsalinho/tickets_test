package com.s21.data.mappers

import com.s21.data.network.model.OfferDto
import com.s21.data.network.model.ValueDto
import com.s21.domain.models.Offer
import com.s21.domain.models.Value


fun OfferDto.toOffer() : Offer {
    return Offer(
        id = this.id,
        title = this.title,
        town = this.town,
        price = this.price.toValue()
    )
}

fun ValueDto.toValue() : Value {
    return Value(
        value = this.value
    )
}