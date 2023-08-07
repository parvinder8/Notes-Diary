package com.parvinderr.notesdiary.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentSplashBinding

class SplashFragment : ViewBindingFragment<FragmentSplashBinding>() {
    override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater, container, false)
    }

    override fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_noteDashboardFragment2)
        }, 2000)
    }
}

