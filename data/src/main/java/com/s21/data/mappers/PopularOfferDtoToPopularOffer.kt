package com.s21.data.mappers

import com.s21.data.network.model.PopularOfferDto
import com.s21.domain.models.PopularOffer

fun PopularOfferDto.toPopularOffer() : PopularOffer {
    return PopularOffer(
        title = this.title,
        status = this.status
    )
}