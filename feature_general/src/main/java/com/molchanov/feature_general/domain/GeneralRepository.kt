package com.molchanov.feature_general.domain

import com.molchanov.feature_general.data.dto.GeneralMenuDto
import io.reactivex.rxjava3.core.Single

interface GeneralRepository {
    fun getGeneralMenu(): Single<GeneralMenuDto>
}