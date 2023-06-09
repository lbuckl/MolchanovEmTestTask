package com.molchanov.feature_general.di

import com.molchanov.feature_general.data.GeneralRepositoryImpl
import com.molchanov.feature_general.domain.GeneralRepository
import dagger.Binds
import dagger.Module

@Module
interface GeneralRepositoryModule {

    @Binds
    fun binGeneralRepository(repository: GeneralRepositoryImpl): GeneralRepository
}