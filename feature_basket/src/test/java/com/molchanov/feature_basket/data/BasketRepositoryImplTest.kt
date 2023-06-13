package com.molchanov.feature_basket.data

import com.molchanov.feature_basket.TestDataProvider
import com.molchanov.repository.domain.BasketDishRepository
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class BasketRepositoryImplTest {

    private var basketRepositoryImpl: BasketRepositoryImpl? = null
    lateinit var basketDishRepository: BasketDishRepository

    private var testDataProvider: TestDataProvider? = null

    @Before
    fun setUp(){
        mock(BasketDishRepository::class.java)
        basketDishRepository = mock(BasketDishRepository::class.java)
        testDataProvider = TestDataProvider()

        basketRepositoryImpl = BasketRepositoryImpl(basketDishRepository)
    }

    @After
    fun setDawn(){
        testDataProvider = null
        basketRepositoryImpl = null
    }

    @Test
    fun getBasketDishTest(){
        `when`(basketDishRepository.getDishes()).thenReturn(
            Single.create{
                testDataProvider?.listTestDishEntity?.let { it1 -> it.onSuccess(it1) }
            }
        )

        val result = basketRepositoryImpl?.getBasketDish()?.blockingGet()

        assertEquals(testDataProvider?.listTestBasketDish, result)

        basketRepositoryImpl?.getBasketDish()?.test()
            ?.assertNoErrors()
    }
}