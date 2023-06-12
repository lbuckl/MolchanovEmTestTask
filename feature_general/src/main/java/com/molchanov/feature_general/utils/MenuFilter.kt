package com.molchanov.feature_general.utils

import com.molchanov.feature_general.domain.Dish

class MenuFilter {

    fun filterMenuData(data: List<Dish>, filter: String): List<Dish> {
        val result = mutableListOf<Dish>()

        data.forEach { dish ->
            for (i in 0 until dish.tegs.size) {
                if (dish.tegs[i] == filter) {
                    result.add(dish)
                    continue
                }
            }
        }
        return result
    }
}