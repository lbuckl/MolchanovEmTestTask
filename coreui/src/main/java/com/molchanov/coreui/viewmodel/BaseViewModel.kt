package com.molchanov.coreui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Базовый класс для всех ViewModel работающих с RxJava
 */
abstract class BaseViewModel<V : AppState>() : ViewModel() {

    protected val liveData: MutableLiveData<V> = MutableLiveData<V>()

    private val outLiveData: LiveData<V> by lazy {
        liveData
    }

    abstract fun getData(page: Int)

    fun getMyLiveData(): LiveData<V> {

        if (liveData.value == null) getData(1)

        return outLiveData
    }
}