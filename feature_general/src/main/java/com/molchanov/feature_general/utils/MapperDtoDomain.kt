package com.molchanov.feature_general.utils

import com.molchanov.feature_general.data.dto.menu.DishDto
import com.molchanov.feature_general.domain.Dish

class MapperDtoDomain {
    
    fun dishDtoToDomain(dishDto: DishDto): Dish {
        return Dish (
            dishDto.id,
            dishDto.name,
            dishDto.description,
            dishDto.price,
            dishDto.weight,
            dishDto.imageUrl,
            dishDto.tegs
        )
    }
}