package com.molchanov.feature_general.domain

import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto
import io.reactivex.rxjava3.core.Single

interface GeneralRepository {
    fun getCategories(): Single<GeneralMenuDto>

    fun getAsianMenu(): Single<MenuItemsDto>
}