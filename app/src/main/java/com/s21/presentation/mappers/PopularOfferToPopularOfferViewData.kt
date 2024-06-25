package com.s21.presentation.mappers

import com.s21.domain.models.PopularOffer
import com.s21.presentation.models.PopularOfferViewData


fun PopularOffer.toPopularOfferViewData() : PopularOfferViewData {
    return PopularOfferViewData(
        title = this.title,
        status = this.status
    )
}