package com.molchanov.coreui.di

import androidx.lifecycle.ViewModelProvider
import com.molchanov.coreui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
