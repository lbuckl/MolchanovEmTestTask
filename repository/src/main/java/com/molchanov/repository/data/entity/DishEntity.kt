package com.molchanov.repository.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Классы для харения данных в БД Room
 */
@Entity(tableName = "BasketDishTab")
data class DishEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val quantity: Int
)