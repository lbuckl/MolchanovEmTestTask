package com.molchanov.core.di

import android.app.Application
import android.content.Context

interface AndroidDependenciesProvider {

    fun provideApplication(): Application

    fun provideContext(): Context
}