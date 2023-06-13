package com.molchanov.feature_basket

import com.molchanov.feature_basket.domain.BasketDish
import com.molchanov.repository.data.entity.DishEntity

class TestDataProvider {

    val dishId = 1
    val dishName = "Блюдо 1"
    val dishPrice = 500
    val dishWeight = 200
    val dishUrl = "SomeUrl"
    val dishQuantity = 1

    val testDishEntity = DishEntity(
        id = dishId,
        name = dishName,
        price = dishPrice,
        weight = dishWeight,
        imageUrl = dishUrl,
        quantity = dishQuantity
    )

    val testDishEntityWrongId = DishEntity(
        id = 5,
        name = dishName,
        price = dishPrice,
        weight = dishWeight,
        imageUrl = dishUrl,
        quantity = dishQuantity
    )

    val testBasketDish = BasketDish(
        id = 1,
        name = "Блюдо 1",
        price = 500,
        weight = 200,
        imageUrl = "SomeUrl",
        quantity = 1
    )

    val testBasketDishWrongId = BasketDish(
        id = 5,
        name = "Блюдо 1",
        price = 500,
        weight = 200,
        imageUrl = "SomeUrl",
        quantity = 1
    )

    val listTestDishEntity = listOf(
        testDishEntity, testDishEntityWrongId
    )

    val listTestBasketDish = listOf(
        testBasketDish, testBasketDishWrongId
    )
}