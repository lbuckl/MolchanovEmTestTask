package com.molchanov.feature_general.data

import com.molchanov.feature_general.data.dto.GeneralMenuDto
import com.molchanov.feature_general.domain.GeneralRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
    private val generalApi: GeneralApi
): GeneralRepository {
    override fun getGeneralMenu(): Single<GeneralMenuDto>{
        return generalApi.getGeneralMenu()
    }
}