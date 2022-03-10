package com.intech.xelauncher

import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        lateinit var instance: Application
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        instance = this
    }

}