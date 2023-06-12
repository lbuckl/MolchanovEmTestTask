package com.molchanov.repository.data

import com.molchanov.repository.data.entity.DishEntity
import com.molchanov.repository.domain.BasketDishRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BasketDishRepositoryImpl @Inject constructor(
    database: BasketDishDb
): BasketDishRepository {

    private val dao = database.getDAO()

    override fun addDish(dish: DishEntity) {
        Completable.create {
            dao.insertBasketDish(dish)
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun replaceDish(dish: DishEntity): Completable {
        return Completable.create {
            dao.updateBasketDish(dish)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    override fun getDishes(): Single<List<DishEntity>> {
        return dao.queryBasketDishes()
    }

    override fun deleteDish(dish: DishEntity): Completable {
        return Completable.create {
            dao.deleteBasketDish(dish)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }
}