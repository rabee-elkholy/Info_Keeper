package com.androdu.infokeeper

import android.app.Application
import com.androdu.infokeeper.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * Custom Application class for the InfoKeeper app.
 *
 * This class initializes the Koin dependency injection framework during the application's startup.
 * It sets up the Koin context and loads the application module to provide dependencies throughout the app.
 */
class InfoKeeperApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin with the application context and application module
        startKoin {
            androidContext(this@InfoKeeperApp)
            modules(appModule)
        }
    }
}
