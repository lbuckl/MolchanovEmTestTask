package com.molchanov.feature_general.di

import androidx.lifecycle.ViewModel
import com.molchanov.coreui.viewmodel.ViewModelKey
import com.molchanov.feature_general.presentation.categories.CategoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GeneralViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel
}