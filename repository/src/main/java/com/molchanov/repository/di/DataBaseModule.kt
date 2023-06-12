package com.molchanov.repository.di

import android.content.Context
import androidx.room.Room
import com.molchanov.repository.data.BasketDishDb
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    private val basketDishDb: BasketDishDb? = null

    @Provides
    fun getCharacterEpisodeDB(context: Context): BasketDishDb {
        return basketDishDb
            ?: Room.databaseBuilder(
                context,
                BasketDishDb::class.java,
                BasketDishDb.BASKET_DISH_DB_NAME
            ).build()
    }
}