package com.s21.presentation.mappers

import com.s21.domain.models.Offer
import com.s21.presentation.models.OfferViewData

fun Offer.toOfferDataView() : OfferViewData {
    return OfferViewData(
        id = this.id,
        title = this.title,
        town = this.town,
        price = this.price.toValueViewData()
    )
}
