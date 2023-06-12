package com.molchanov.feature_basket.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.di.RouterModule
import com.molchanov.feature_basket.presentation.BasketFragment
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        RouterModule::class,
        BasketRepositoryModule::class,
        BasketViewModelModule::class
    ]
)
interface BasketComponent {

    companion object {
        fun init(
            applicationProvider: ApplicationProvider
        ): BasketComponent {
            return DaggerBasketComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): BasketComponent
    }

    fun inject(fragment: BasketFragment)
}