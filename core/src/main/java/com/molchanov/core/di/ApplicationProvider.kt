package com.molchanov.core.di

import com.molchanov.core.di.network.NetworkProvider

interface ApplicationProvider:
    AndroidDependenciesProvider,
    NetworkProvider