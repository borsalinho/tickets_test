package com.s21.data.network.model

data class OfferDto (
    val id: Int,
    val title: String,
    val town: String,
    val price: ValueDto
)