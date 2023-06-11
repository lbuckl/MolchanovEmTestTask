package com.molchanov.feature_general.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.molchanov.core.data.date.AppDateLocal
import com.molchanov.core.data.geoposition.Geolocation
import com.molchanov.core.domain.LocationAndDate
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.appdata.SingleNotifyLiveData
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_general.data.dto.categories.GeneralMenuDto
import com.molchanov.feature_general.domain.GeneralRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: GeneralRepository,
    private val geolocator: Geolocation,
    private val dateProvider: AppDateLocal
): BaseViewModel<DefaultAppState>() {

    private val _isLoadingEvent = SingleNotifyLiveData<Boolean>()
    val isLoadingEvent: LiveData<Boolean> = _isLoadingEvent

    private val _locationPermission = SingleNotifyLiveData<Boolean>()
    val location: LiveData<Boolean> = _locationPermission

    private val _locationLiveData: MutableLiveData<LocationAndDate> = MutableLiveData()
    val locationLiveData: LiveData<LocationAndDate> = _locationLiveData

    fun getData() {
        _isLoadingEvent.value = true
        repository.getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveData.value = DefaultAppState.Success<GeneralMenuDto>(it)
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

    fun getLocation() {
        geolocator.getLocation().let {
            _locationPermission.value = it
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