package com.molchanov.coreui.viewmodel.appstate

sealed class DefaultAppState: AppState() {
    class Success<AppData>(val data: AppData): DefaultAppState()
    class Error(error: Throwable): DefaultAppState()
}