package com.molchanov.core.di.android

import android.app.Application
import com.molchanov.core.di.AndroidDependenciesProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidDependenciesModule::class
    ]
)
interface AndroidDependenciesComponent: AndroidDependenciesProvider {

    companion object {

        fun create(app: Application): AndroidDependenciesProvider {

            val androidDependenciesModule = AndroidDependenciesModule(app)

            return DaggerAndroidDependenciesComponent.builder()
                .androidDependenciesModule(androidDependenciesModule)
                .build()
        }
    }
}