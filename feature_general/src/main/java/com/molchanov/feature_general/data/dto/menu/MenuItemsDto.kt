package com.molchanov.feature_general.data.dto.menu


import com.google.gson.annotations.SerializedName

data class MenuItemsDto(
    @SerializedName("dishes")
    val dishes: List<DishDto>
)