package com.molchanov.feature_general.di

import com.molchanov.core.data.network.RetrofitImpl
import com.molchanov.feature_general.data.GeneralApi
import dagger.Module
import dagger.Provides

@Module
class GeneralApiModule {

    @Provides
    fun provideCharactersApi(retrofit: RetrofitImpl): GeneralApi {
        return retrofit.getRetrofit().create(GeneralApi::class.java)
    }
}