package com.molchanov.feature_general.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.di.RouterModule
import com.molchanov.feature_general.presentation.categories.CategoryFragment
import com.molchanov.feature_general.presentation.menu.MenuFragment
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        RouterModule::class,
        GeneralApiModule::class,
        GeneralViewModelModule::class,
        GeneralRepositoryModule::class,
    ]
)
interface GeneralComponent {

    companion object {
        fun init(
            applicationProvider: ApplicationProvider
        ): GeneralComponent {
            return DaggerGeneralComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): GeneralComponent
    }

    fun inject (fragment: CategoryFragment)
    fun inject (fragment: MenuFragment)
}