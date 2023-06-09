package com.molchanov.molchanovemtesttask.di

import com.molchanov.core.di.ApplicationProvider
import com.molchanov.coreui.di.RouterModule
import com.molchanov.molchanovemtesttask.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RouterModule::class
    ],
    dependencies = [
        ApplicationProvider::class,
    ]
)
interface MainActivityComponent {

    companion object {
        fun init(applicationProvider: ApplicationProvider): MainActivityComponent {
            return DaggerMainActivityComponent.factory()
                .create(applicationProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            applicationProvider: ApplicationProvider
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}