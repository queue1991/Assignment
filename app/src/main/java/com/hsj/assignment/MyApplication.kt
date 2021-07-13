package com.hsj.assignment

import android.app.Application
import com.hsj.assignment.di.myDiModule
import org.koin.android.ext.android.startKoin

/**
 * Start Koin(DI)
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)
    }
}