package com.hashconcepts.composeinstagramclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

/**
 * @created 29/07/2022 - 10:44 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@HiltAndroidApp
class InstagramCloneApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }
}