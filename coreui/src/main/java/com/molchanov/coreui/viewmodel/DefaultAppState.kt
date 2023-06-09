package com.molchanov.coreui.viewmodel

sealed class DefaultAppState: AppState() {
    class Success<AppData>(data: AppData): DefaultAppState()
    class Error(error: Throwable): DefaultAppState()
}