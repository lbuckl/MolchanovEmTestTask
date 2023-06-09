package com.molchanov.feature_general.data.dto


import com.google.gson.annotations.SerializedName

data class GeneralMenuDto(
    @SerializedName("сategories")
    val categories: List<MenuCategory>
)