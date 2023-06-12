package com.molchanov.repository.domain

data class DishQuantity(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val tegs: List<String>,
    val quantity: Int
)