package com.molchanov.core.di.android

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidDependenciesModule (
    private val application: Application,
    ) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application
}