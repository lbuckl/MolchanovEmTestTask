package com.molchanov.repository.data

import androidx.room.*
import com.molchanov.repository.data.entity.DishEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface BasketDishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBasketDish(character: DishEntity)

    @Update
    fun updateBasketDish(character: DishEntity)

    @Query("SELECT * FROM BasketDishTab WHERE id = :id")
    fun queryCharacterByUrl(id: Int): Single<DishEntity>

    @Delete
    fun deleteBasketDish(character: DishEntity)
}