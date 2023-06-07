package com.molchanov.core.di.network

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)

interface NetworkComponent: NetworkProvider {

    companion object {

        fun create(): NetworkProvider {
            return DaggerNetworkComponent.builder().build()
        }
    }
}