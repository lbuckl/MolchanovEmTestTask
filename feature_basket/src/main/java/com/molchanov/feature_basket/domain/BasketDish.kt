package com.molchanov.feature_basket.domain

data class BasketDish(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val quantity: Int
)