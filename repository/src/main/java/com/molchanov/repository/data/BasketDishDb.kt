package com.molchanov.repository.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molchanov.repository.data.entity.DishEntity

@Database(
    entities = [
        DishEntity::class
    ], version = 1
)
abstract class BasketDishDb: RoomDatabase() {

    companion object {
        const val BASKET_DISH_DB_NAME = "CHARACTER_EPISODE_DB"
    }

    abstract fun getDAO(): BasketDishDao
}