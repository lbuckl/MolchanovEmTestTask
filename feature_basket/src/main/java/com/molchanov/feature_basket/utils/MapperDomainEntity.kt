package com.molchanov.feature_basket.utils

import com.molchanov.feature_basket.domain.BasketDish
import com.molchanov.repository.data.entity.DishEntity

class MapperDomainEntity {

    fun basketDishEntityToDomain(dishEntity: DishEntity): BasketDish {
        return BasketDish(
            dishEntity.id,
            dishEntity.name,
            dishEntity.price,
            dishEntity.weight,
            dishEntity.imageUrl,
            dishEntity.quantity
        )
    }
}