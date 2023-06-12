package com.molchanov.feature_basket.data

import com.molchanov.feature_basket.domain.BasketDish
import com.molchanov.feature_basket.domain.BasketRepository
import com.molchanov.feature_basket.utils.MapperDomainEntity
import com.molchanov.repository.domain.BasketDishRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDishRepository: BasketDishRepository
): BasketRepository {

    override fun getBasketDish(): Single<List<BasketDish>> {
        return basketDishRepository.getDishes()
            .subscribeOn(Schedulers.io())
            .flatMap { list->
                Single.create {
                    it.onSuccess(
                        list.map { dishEntity->
                            MapperDomainEntity().basketDishEntityToDomain(dishEntity)
                        }
                    )
                }
            }
    }
}