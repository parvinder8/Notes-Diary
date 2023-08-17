package com.parvinderr.notesdiary.ui.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentSettingBinding
import com.parvinderr.notesdiary.preference.SettingPreferenceHelper
import com.parvinderr.notesdiary.ui.MainActivity
import com.parvinderr.notesdiary.utils.FontFamilyEnum
import com.parvinderr.notesdiary.utils.LayoutEnum
import com.parvinderr.notesdiary.utils.ThemeEnum
import com.parvinderr.notesdiary.utils.showToast

class SettingFragment : ViewBindingFragment<FragmentSettingBinding>() {
    override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setUpViews()
        clickListeners()
    }

    private fun clickListeners() {
        with(binding) {
            cvGithub.setOnClickListener {
                showToast("Github")
            }
            cvPrivacyPolicy.setOnClickListener {
                showToast("Privacy Policy")
            }

            spinnerLayoutType.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val tempSelectedData = parent?.selectedItem as LayoutEnum? ?: return
                    SettingPreferenceHelper.preference.setLayoutPreference(tempSelectedData)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            spinnerAppTheme.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val tempSelectedData = parent?.selectedItem as ThemeEnum? ?: return
                    (activity as MainActivity).setAppTheme(tempSelectedData)
                    SettingPreferenceHelper.preference.setThemePreference(tempSelectedData)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            spinnerFontFamily.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val tempSelectedData = parent?.selectedItem as FontFamilyEnum? ?: return
                    (activity as MainActivity).changeFontFamily(tempSelectedData)
                    SettingPreferenceHelper.preference.setFontFamilyPreference(tempSelectedData)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun setUpViews() {
        val themeData = ThemeEnum.values()
        val selectedTheme = SettingPreferenceHelper.preference.getThemePreference()

        val themeAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, themeData
        )
        binding.spinnerAppTheme.adapter = themeAdapter
        binding.spinnerAppTheme.setSelection(themeData.indexOf(selectedTheme))


        val layoutData = LayoutEnum.values()
        val selectedLayout = SettingPreferenceHelper.preference.getLayoutPreference()

        val layoutAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, layoutData
        )
        binding.spinnerLayoutType.adapter = layoutAdapter
        binding.spinnerLayoutType.setSelection(layoutData.indexOf(selectedLayout))

        val fontFamilyData = FontFamilyEnum.values()
        val selectedFontFamily = SettingPreferenceHelper.preference.getFontFamilyPreference()

        val fontFamilyAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, fontFamilyData
        )
        binding.spinnerFontFamily.adapter = fontFamilyAdapter
        binding.spinnerFontFamily.setSelection(fontFamilyData.indexOf(selectedFontFamily))

    }

}