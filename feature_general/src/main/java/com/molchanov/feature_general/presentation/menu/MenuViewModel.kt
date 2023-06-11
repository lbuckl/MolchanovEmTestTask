package com.molchanov.feature_general.presentation.menu

import androidx.lifecycle.LiveData
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appdata.SingleNotifyLiveData
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.data.dto.menu.MenuItemsDto
import com.molchanov.feature_general.domain.GeneralRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val repository: GeneralRepository
): BaseViewModel<DefaultAppState>() {

    private val _isLoadingEvent = SingleNotifyLiveData<Boolean>()
    val isLoadingEvent: LiveData<Boolean> = _isLoadingEvent

    fun getAsianMenu() {
        _isLoadingEvent.value = true
        repository.getAsianMenu()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveData.value = DefaultAppState.Success<MenuItemsDto>(it)
                    repository.saveLastMenu(it)
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
}