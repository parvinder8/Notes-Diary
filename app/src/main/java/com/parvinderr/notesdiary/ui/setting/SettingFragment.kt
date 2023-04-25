package com.parvinderr.notesdiary.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentSettingBinding

class SettingFragment : ViewBindingFragment<FragmentSettingBinding>() {
    override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}