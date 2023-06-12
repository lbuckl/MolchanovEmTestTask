package com.molchanov.feature_basket.domain

import io.reactivex.rxjava3.core.Single

interface BasketRepository {
    fun getBasketDish(): Single<List<BasketDish>>
}