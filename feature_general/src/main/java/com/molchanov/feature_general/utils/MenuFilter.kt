package com.molchanov.feature_general.utils

import com.molchanov.feature_general.data.dto.menu.Dish
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto

class MenuFilter {

    fun filterMenuData(data: MenuItemsDto, filter: String): MenuItemsDto {
        val result = mutableListOf<Dish>()

        data.dishes.forEach { dish ->
            for (i in 0 until dish.tegs.size) {
                if (dish.tegs[i] == filter) {
                    result.add(dish)
                    continue
                }
            }
        }
        return MenuItemsDto(result)
    }
}