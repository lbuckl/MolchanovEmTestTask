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

    override fun plusDish(dish: BasketDish): Single<List<BasketDish>> {
        return replaceBasketDish(dish.copy(quantity = dish.quantity + 1))
    }

    override fun minusDIsh(dish: BasketDish): Single<List<BasketDish>> {
        return if (dish.quantity - 1 == 0) {
            deleteDish(dish)
        }
        else replaceBasketDish(dish.copy(quantity = dish.quantity - 1))
    }

    private fun replaceBasketDish(dish: BasketDish): Single<List<BasketDish>>{
        return Single.create { emitter->
            basketDishRepository.replaceDish(
                MapperDomainEntity().basketDomainToDishEntity(dish)
            ).doOnComplete {
                getBasketDish().subscribe(
                    {
                        emitter.onSuccess(it)
                    },
                    {
                        emitter.onError(it)
                    }
                )
            }.subscribe()
        }.subscribeOn(Schedulers.io())
    }

    private fun deleteDish(dish: BasketDish): Single<List<BasketDish>>{
        return Single.create { emitter->
            basketDishRepository.deleteDish(
                MapperDomainEntity().basketDomainToDishEntity(dish)
            ).doOnComplete {
                getBasketDish().subscribe(
                    {
                        emitter.onSuccess(it)
                    },
                    {
                        emitter.onError(it)
                    }
                )
            }.subscribe()
        }.subscribeOn(Schedulers.io())
    }
}