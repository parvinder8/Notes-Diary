package com.parvinderr.notesdiary

import android.app.Application
import com.parvinderr.notesdiary.preference.SettingPreference
import com.parvinderr.notesdiary.preference.SettingPreferenceHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        SettingPreferenceHelper(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}