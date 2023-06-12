package com.molchanov.feature_general.domain

import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import io.reactivex.rxjava3.core.Single

interface GeneralRepository {
    fun getCategories(): Single<GeneralMenuDto>

    fun getAsianMenu(): Single<List<Dish>>

    fun getFilteredMenu(filter: String): List<Dish>

    fun saveLastMenu(menu: List<Dish>)

    fun saveDishInBasket(dish: Dish)
}