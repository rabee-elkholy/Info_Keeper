package com.androdu.infokeeper

import android.app.Application
import com.androdu.infokeeper.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class InfoKeeperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InfoKeeperApp)
            modules(appModule)
        }
    }
}
