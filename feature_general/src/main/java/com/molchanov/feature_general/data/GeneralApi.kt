package com.molchanov.feature_general.data

import com.molchanov.feature_general.data.dto.GeneralMenuDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GeneralApi {

    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    fun getGeneralMenu(): Single<GeneralMenuDto>
}