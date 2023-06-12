package com.molchanov.feature_general.utils

import com.molchanov.feature_general.domain.Dish
import com.molchanov.repository.data.entity.DishEntity

class MapperDomainEntity {

    fun dishDomainToEntity(dish: Dish) : DishEntity {
        return DishEntity(
            dish.id,
            dish.name,
            dish.price,
            dish.weight,
            dish.imageUrl,
            1
        )
    }
}