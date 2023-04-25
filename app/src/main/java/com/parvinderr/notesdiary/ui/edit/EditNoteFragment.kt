package com.parvinderr.notesdiary.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentEditNoteBinding

class EditNoteFragment : ViewBindingFragment<FragmentEditNoteBinding>() {
    override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentEditNoteBinding {
        return FragmentEditNoteBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}