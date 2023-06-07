package com.molchanov.core.di.network

import com.molchanov.core.domain.IRetrofitProvider

interface NetworkProvider {
    fun provideRetrofit(): IRetrofitProvider
}