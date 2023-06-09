package com.molchanov.coreui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.molchanov.core.di.App
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.viewmodel.AppState
import com.molchanov.coreui.viewmodel.BaseViewModel
import com.molchanov.coreui.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Базовый класс для фрагментов с ViewModel
 */
abstract class BaseVmFragment<T : ViewBinding, AS : AppState, VM : BaseViewModel<AS>> :
    BaseFragment<T>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject((requireActivity().application as App).getApplicationProvider())

        return binding.root
    }

    abstract fun inject(applicationProvider: ApplicationProvider)
}