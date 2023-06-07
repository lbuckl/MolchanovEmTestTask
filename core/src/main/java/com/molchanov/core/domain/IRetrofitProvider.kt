package com.molchanov.core.domain

import retrofit2.Retrofit

interface IRetrofitProvider {
    fun getRetrofit(): Retrofit
}