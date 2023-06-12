package com.molchanov.feature_basket.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.molchanov.core.data.date.AppDateLocal
import com.molchanov.core.data.geoposition.Geolocation
import com.molchanov.core.domain.LocationAndDate
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appdata.SingleNotifyLiveData
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_basket.domain.BasketDish
import com.molchanov.feature_basket.domain.BasketRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class BasketViewModel @Inject constructor(
    private val basketRepository: BasketRepository,
    private val geolocator: Geolocation,
    private val dateProvider: AppDateLocal
) : BaseViewModel<DefaultAppState>() {

    init {
        getLocation()
    }

    private val _locationLiveData: MutableLiveData<LocationAndDate> = MutableLiveData()
    val locationLiveData: LiveData<LocationAndDate> = _locationLiveData

    private val _priceLiveData: SingleNotifyLiveData<Int> = SingleNotifyLiveData()
    val priceLiveData: LiveData<Int> = _priceLiveData

    fun getBasket() {
        basketRepository.getBasketDish()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(basketObserver)
    }

    fun actionPlus(data: BasketDish) {
        basketRepository.plusDish(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(basketObserver)
    }

    fun actionMinus(data: BasketDish) {
        basketRepository.minusDIsh(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(basketObserver)
    }

    private val basketObserver = object : SingleObserver<List<BasketDish>> {
        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        override fun onSuccess(t: List<BasketDish>) {
            liveData.value = DefaultAppState.Success(t)
            _priceLiveData.value = calculateSumPrice(t)
        }

        override fun onError(e: Throwable) {
            liveData.value = DefaultAppState.Error(e)
        }
    }

    private fun calculateSumPrice(prices: List<BasketDish>): Int {
        var price = 0
        prices.forEach {
            price += it.price * it.quantity
        }
        return price
    }

    private fun getLocation() {
        geolocator.getLocation().let {
            if (it) observeLocationAndDate()
        }
    }

    private fun observeLocationAndDate() {
        geolocator.getLocationObserver().subscribe(
            { location ->
                _locationLiveData.value = LocationAndDate(
                    location, dateProvider.getDate()
                )
            },
            {
                _locationLiveData.value = LocationAndDate("", "")
            }
        ).also { compositeDisposable.add(it) }
    }
}