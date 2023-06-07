package com.molchanov.coreui.di

import com.molchanov.coreui.router.IRouter
import com.molchanov.coreui.router.Router
import dagger.Binds
import dagger.Module

@Module
interface RouterModule {

    @Binds
    fun bindRouter(router: Router):IRouter
}