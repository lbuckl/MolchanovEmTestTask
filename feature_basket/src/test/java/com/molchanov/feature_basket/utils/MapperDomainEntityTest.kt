package com.molchanov.feature_basket.utils

import com.molchanov.feature_basket.TestDataProvider
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MapperDomainEntityTest {

    private var mapper: MapperDomainEntity? = null
    private var testDataProvider: TestDataProvider? = null

    @Before
    fun setUp(){
        mapper = MapperDomainEntity()
        testDataProvider = TestDataProvider()
    }

    @After
    fun setDawn(){
        mapper = null
        testDataProvider = null
    }

    @Test
    fun basketDishEntityToDomain_needToBeeEquals() {
        val result = testDataProvider?.testBasketDish?.let { mapper?.basketDomainToDishEntity(it) }

        assertEquals(result,testDataProvider?.testDishEntity)
        assertNotEquals(result, testDataProvider?.testDishEntityWrongId)
    }

    @Test
    fun basketDomainToDishEntity_needToBeeEquals() {
        val result = testDataProvider?.testDishEntity?.let { mapper?.basketDishEntityToDomain(it) }

        assertEquals(result,testDataProvider?.testBasketDish)
        assertNotEquals(result, testDataProvider?.testBasketDishWrongId)
    }
}