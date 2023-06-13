package com.molchanov.feature_basket.presentation

import android.content.Context
import com.molchanov.core.data.date.AppDateLocal
import com.molchanov.core.data.geoposition.Geolocation
import com.molchanov.feature_basket.domain.BasketRepository
import com.molchanov.feature_basket.TestDataProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class BasketViewModelTest {

    private var basketViewModel: BasketViewModel? = null

    private var basketRepository: BasketRepository? = null

    private var context: Context? = null

    private var testDataProvider: TestDataProvider? = null

    @Before
    fun setUp(){

        context = mock(Context::class.java)
        basketRepository = mock(BasketRepository::class.java)
        testDataProvider = TestDataProvider()

        basketViewModel = BasketViewModel(
            basketRepository!!,
            Geolocation(context!!),
            AppDateLocal()
        )
    }

    @After
    fun setDawn(){
        context = null
        basketRepository = null
        testDataProvider = null
        basketViewModel = null
    }

    @Test
    fun getBasketTest() {
        `when`(basketRepository?.getBasketDish()
            ?.observeOn(AndroidSchedulers.mainThread())
        ).thenReturn(
            Single.create {
                testDataProvider?.listTestBasketDish?.let { it1 ->
                    it.onSuccess(
                        it1
                    )
                }
            }
        )

        basketRepository?.getBasketDish()?.test()
            ?.assertNoErrors()
            ?.assertComplete()
            ?.assertValue {
                it[0] == testDataProvider?.listTestBasketDish?.get(0)
            }
            ?.assertValue {
                it[1] == testDataProvider?.listTestBasketDish?.get(1)
            }
    }
}