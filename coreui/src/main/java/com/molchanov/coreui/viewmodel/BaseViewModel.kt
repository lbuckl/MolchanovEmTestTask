package com.molchanov.coreui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molchanov.coreui.viewmodel.appstate.AppState
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Базовый класс для всех ViewModel работающих с RxJava
 */
abstract class BaseViewModel<V : AppState>() : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private val liveData: MutableLiveData<V> = MutableLiveData<V>()
    val outLiveData: LiveData<V> = liveData

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}