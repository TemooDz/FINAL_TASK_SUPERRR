package com.forfinal.SUPER

import android.app.Application
import com.forfinal.SUPER.di.AppContainer

class App : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(applicationContext)
    }
}
