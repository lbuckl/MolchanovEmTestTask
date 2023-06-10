package com.molchanov.feature_general.data.dto.categories


import com.google.gson.annotations.SerializedName

data class MenuCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)