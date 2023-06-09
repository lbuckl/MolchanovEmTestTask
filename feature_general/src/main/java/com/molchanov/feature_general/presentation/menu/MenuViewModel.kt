package com.molchanov.feature_general.presentation.menu

import androidx.lifecycle.LiveData
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appdata.SingleNotifyLiveData
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.domain.Dish
import com.molchanov.feature_general.domain.GeneralRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val repository: GeneralRepository
): BaseViewModel<DefaultAppState>() {

    private val _isLoadingEvent = SingleNotifyLiveData<Boolean>()
    val isLoadingEvent: LiveData<Boolean> = _isLoadingEvent

    private var dishList: List<Dish> = listOf()

    fun getAsianMenu() {
        _isLoadingEvent.value = true
        repository.getAsianMenu()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveData.value = DefaultAppState.Success<List<Dish>>(it)
                    repository.saveLastMenu(it)
                    dishList = it
                    _isLoadingEvent.value = false
                },
                {
                    liveData.value = DefaultAppState.Error(it)
                    _isLoadingEvent.value = false
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun getVariant(variant: MenuFilters){
        _isLoadingEvent.value = true
        liveData.value = DefaultAppState.Success(
            repository.getFilteredMenu(variant.filter)
        )
        _isLoadingEvent.value = false
    }

    fun saveDishInBasket(dishId: Int) {
        val dish = dishList.find {
            it.id == dishId
        }

        repository.saveDishInBasket(dish!!)
    }
}