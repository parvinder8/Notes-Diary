package com.parvinderr.notesdiary.preference

import android.content.Context


class SettingPreferenceHelper(context: Context) {

    init {
        preference = SettingPreference(context)
    }

    companion object {
        lateinit var preference: SettingPreference
    }
}