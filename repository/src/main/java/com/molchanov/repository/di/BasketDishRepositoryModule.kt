package com.molchanov.repository.di

import com.molchanov.repository.data.BasketDishRepositoryImpl
import com.molchanov.repository.domain.BasketDishRepository
import dagger.Binds
import dagger.Module

@Module
interface BasketDishRepositoryModule {

    @Binds
    fun bindBasketDishRepository(repository: BasketDishRepositoryImpl): BasketDishRepository
}