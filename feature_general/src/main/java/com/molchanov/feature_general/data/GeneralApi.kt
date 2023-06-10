package com.molchanov.feature_general.data

import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GeneralApi {

    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    fun requestCategories(): Single<GeneralMenuDto>

    @GET("v3/aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    fun requestAsianMenu(): Single<MenuItemsDto>
}