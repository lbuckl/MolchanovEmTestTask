package com.molchanov.feature_basket.di

import androidx.lifecycle.ViewModel
import com.molchanov.coreui.viewmodel.ViewModelKey
import com.molchanov.feature_basket.presentation.BasketViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BasketViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BasketViewModel::class)
    fun bindCategoryViewModel(viewModel: BasketViewModel): ViewModel
}