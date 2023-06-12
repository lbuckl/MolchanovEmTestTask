package com.molchanov.feature_basket.di

import com.molchanov.feature_basket.data.BasketRepositoryImpl
import com.molchanov.feature_basket.domain.BasketRepository
import dagger.Binds
import dagger.Module

@Module
interface BasketRepositoryModule {

    @Binds
    fun bindBasketRepository(repository: BasketRepositoryImpl): BasketRepository
}