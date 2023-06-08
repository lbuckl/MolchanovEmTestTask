package com.molchanov.molchanovemtesttask.di

import com.molchanov.core.di.AndroidDependenciesProvider
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.core.di.android.AndroidDependenciesComponent
import com.molchanov.core.di.network.NetworkComponent
import com.molchanov.core.di.network.NetworkProvider
import com.molchanov.coreui.di.ViewModelFactoryModule
import com.molchanov.molchanovemtesttask.TestTaskApp
import dagger.Component

@Component(
    dependencies = [
        AndroidDependenciesProvider::class,
        NetworkProvider::class
    ],
    modules = [
        ApplicationModule::class,
        ViewModelFactoryModule::class
    ]
)
interface ApplicationComponent: ApplicationProvider {

    companion object {

        fun init(app: TestTaskApp): ApplicationProvider {

            val androidDependenciesProvider = AndroidDependenciesComponent.create(app)
            val networkProvider = NetworkComponent.create()

            return DaggerApplicationComponent.factory()
                .create(
                    androidDependenciesProvider,
                    networkProvider
                )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            androidDependenciesProvider: AndroidDependenciesProvider,
            networkProvider: NetworkProvider
        ): ApplicationComponent
    }
}