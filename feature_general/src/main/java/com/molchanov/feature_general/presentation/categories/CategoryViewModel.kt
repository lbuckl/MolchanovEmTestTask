package com.molchanov.feature_general.presentation.categories

import androidx.lifecycle.LiveData
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appdata.SingleNotifyLiveData
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.domain.GeneralRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: GeneralRepository
): BaseViewModel<DefaultAppState>() {

    private val _isLoadingEvent = SingleNotifyLiveData<Boolean>()
    val isLoadingEvent: LiveData<Boolean> = _isLoadingEvent

    fun getData() {
        repository.getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveData.value = DefaultAppState.Success<GeneralMenuDto>(it)
                },
                {
                    liveData.value = DefaultAppState.Error(it)
                }
            ).also { compositeDisposable.add(it) }
    }
}