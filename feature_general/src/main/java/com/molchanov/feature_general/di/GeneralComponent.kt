package com.molchanov.feature_general.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.di.RouterModule
import dagger.Component

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        RouterModule::class,
        GeneralApiModule::class
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

    //fun inject (fragment: CharactersFragment)
}