package com.molchanov.feature_basket.presentation

import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_basket.domain.BasketRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class BasketViewModel @Inject constructor(
    private val basketRepository: BasketRepository
): BaseViewModel<DefaultAppState>() {

    fun getBasket(){
        basketRepository.getBasketDish()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                liveData.value = DefaultAppState.Success(it)
            },
            {
                //TODO
            }
        ).also { compositeDisposable.add(it) }
    }
}