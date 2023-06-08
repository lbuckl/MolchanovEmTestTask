package com.molchanov.core.di

import android.app.Application
import android.content.Context
import android.content.res.Resources

interface AndroidDependenciesProvider {

    fun provideApplication(): Application

    fun provideContext(): Context

    fun provideResources(): Resources
}