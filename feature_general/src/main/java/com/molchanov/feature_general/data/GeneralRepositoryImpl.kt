package com.molchanov.feature_general.data

import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto
import com.molchanov.feature_general.domain.Dish
import com.molchanov.feature_general.domain.GeneralRepository
import com.molchanov.feature_general.utils.MapperDtoDomain
import com.molchanov.feature_general.utils.MenuFilter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
    private val generalApi: GeneralApi
): GeneralRepository {

    private var lastMenuItemsDto = listOf<Dish>()

    override fun getCategories(): Single<GeneralMenuDto> {
        return generalApi.requestCategories().subscribeOn(Schedulers.io())
    }

    override fun getAsianMenu(): Single<List<Dish>> {
        return generalApi.requestAsianMenu()
            .map { menu->
                menu.dishes.map {
                    MapperDtoDomain().dishDtoToDomain(it)
                }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getFilteredMenu(filter: String): List<Dish> {
        return if (lastMenuItemsDto.isNotEmpty()) {
            try {
                MenuFilter().filterMenuData(lastMenuItemsDto, filter)
            }catch (e: java.lang.IndexOutOfBoundsException) {
                e.printStackTrace()
                return lastMenuItemsDto
            }
        } else lastMenuItemsDto
    }

    override fun saveLastMenu(menu: List<Dish>) {
        lastMenuItemsDto = menu
    }
}