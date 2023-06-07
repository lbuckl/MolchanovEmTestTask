package com.molchanov.molchanovemtesttask

import android.app.Application
import com.molchanov.core.di.App
import com.molchanov.core.di.ApplicationProvider
import com.molchanov.molchanovemtesttask.di.ApplicationComponent

class TestTaskApp: Application(), App {

    private lateinit var appComponent: ApplicationProvider

    override fun onCreate() {
        super.onCreate()
        appComponent = ApplicationComponent.init(this)
    }

    override fun getApplicationProvider(): ApplicationProvider {
        return appComponent
    }
}