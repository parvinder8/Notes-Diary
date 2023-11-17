package com.parvinderr.notesdiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.databinding.ActivityMainBinding
import com.parvinderr.notesdiary.preference.SettingPreferenceHelper
import com.parvinderr.notesdiary.utils.FontFamilyEnum
import com.parvinderr.notesdiary.utils.ThemeEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme(SettingPreferenceHelper.preference.getThemePreference())
        changeFontFamily(SettingPreferenceHelper.preference.getFontFamilyPreference())
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun navigationThroughShortCuts() {
        val navController = findNavController(R.id.nav_host_fragment)
        val action = intent.action
        if (action == "android.intent.action.SETTING_SCREEN") {
            navController.navigate(R.id.settingFragment)
        } else if (action == "android.intent.action.EDIT_NOTE") {
            navController.navigate(R.id.editNoteFragment)
        }
    }

    fun changeFontFamily(family: FontFamilyEnum) {
        theme.applyStyle(
            when (family) {
                FontFamilyEnum.DONGLE -> R.style.Dongle
                FontFamilyEnum.POPPINS -> R.style.Poppins
                FontFamilyEnum.SANS -> R.style.SansSerif
                FontFamilyEnum.ROBOTO -> R.style.Roboto
            },
            true,
        )
    }

    override fun onStart() {
        super.onStart()
        setUpViews()
        navigationThroughShortCuts()
    }

    private fun setUpViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
//        listener()
    }

//    private fun listener() {
//        val navHostFragment = findNavController(R.id.nav_host_fragment)
//        navHostFragment.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.editNoteFragment || destination.id == R.id.splashFragment) {
//                binding.bottomNavigationView.gone()
//            } else {
//                binding.bottomNavigationView.visible()
//            }
//        }
//    }

    fun setAppTheme(selectedTheme: ThemeEnum) {
        AppCompatDelegate.setDefaultNightMode(
            when (selectedTheme) {
                ThemeEnum.DEFAULT -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                ThemeEnum.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                ThemeEnum.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            },
        )
    }
}
