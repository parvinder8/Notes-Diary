package com.parvinderr.notesdiary.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ViewBindingFragment<Binding : ViewBinding> : Fragment() {
    private lateinit var _binding: Binding
    protected val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = onBind(inflater, container)
        return _binding.root
    }

    abstract fun onBind(inflater: LayoutInflater, container: ViewGroup?): Binding
}