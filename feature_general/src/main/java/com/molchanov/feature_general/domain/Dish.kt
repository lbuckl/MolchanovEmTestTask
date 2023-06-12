package com.molchanov.feature_general.domain

data class Dish (
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val tegs: List<String>
    )