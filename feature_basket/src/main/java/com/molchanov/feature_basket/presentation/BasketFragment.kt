package com.molchanov.feature_basket.presentation

import androidx.fragment.app.viewModels
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.fragment.BaseVmFragment
import com.molchanov.coreui.viewmodel.appstate.DefaultAppState
import com.molchanov.feature_basket.databinding.FragmentBasketBinding
import com.molchanov.feature_basket.di.BasketComponent

class BasketFragment: BaseVmFragment<FragmentBasketBinding, DefaultAppState, BasketViewModel>() {

    companion object {
        val instance = BasketFragment()

        const val FRAGMENT_TAG = "BasketFragment_fragment_tag"
    }

    override val viewModel: BasketViewModel by viewModels {
        viewModelFactory
    }

    override fun getViewBinding(): FragmentBasketBinding {
        return FragmentBasketBinding.inflate(layoutInflater)
    }

    override fun inject(applicationProvider: ApplicationProvider) {
        BasketComponent.init(applicationProvider).inject(this@BasketFragment)
    }
}