package com.molchanov.core.di.network

import com.molchanov.core.data.network.RetrofitImpl
import com.molchanov.core.domain.IRetrofitProvider
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindRetrofit(retrofit: RetrofitImpl): IRetrofitProvider
}