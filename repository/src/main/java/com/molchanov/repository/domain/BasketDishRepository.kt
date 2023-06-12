package com.molchanov.repository.domain

import com.molchanov.repository.data.entity.DishEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface BasketDishRepository {

    fun addDish(dish: DishEntity)

    fun replaceDish(dish: DishEntity): Completable

    fun getDishes(): Single<List<DishEntity>>

    fun deleteDish(dish: DishEntity): Completable
}