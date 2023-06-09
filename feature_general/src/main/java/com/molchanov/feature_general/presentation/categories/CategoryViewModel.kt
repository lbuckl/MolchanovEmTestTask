package com.molchanov.feature_general.presentation.categories

import androidx.lifecycle.LiveData
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appdata.SingleNotifyLiveData
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.domain.GeneralRepository
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: GeneralRepository
): BaseViewModel<DefaultAppState>() {

    private val _isLoadingEvent = SingleNotifyLiveData<Boolean>()
    val isLoadingEvent: LiveData<Boolean> = _isLoadingEvent

    fun getData() {
        repository.getGeneralMenu()
            .subscribe(
                {

                },
                {

                }
            ).also { compositeDisposable.add(it) }
    }
}