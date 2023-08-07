package com.parvinderr.notesdiary.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentEditNoteBinding
import com.parvinderr.notesdiary.ui.edit.viewmodel.EditViewModel
import com.parvinderr.notesdiary.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class EditNoteFragment @Inject constructor(): ViewBindingFragment<FragmentEditNoteBinding>() {

    private val viewModel by viewModels<EditViewModel>()

    private val warningDialog by lazy {
        AlertDialog.Builder(requireContext())
    }

    override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentEditNoteBinding {
        return FragmentEditNoteBinding.inflate(inflater, container, false)
    }

    override fun init() {
        observers()
        clickListeners()
        listeners()
    }

    private fun listeners() {
        with(binding) {
            etNoteTitle.addTextChangedListener {
                it?.toString()?.let { data ->
                    viewModel.setNoteTitle(data)
                }
            }

            etNoteContent.addTextChangedListener {
                it?.toString()?.let { data ->
                    viewModel.setNotesContent(data)
                }
            }
        }
    }

    private fun clickListeners() {
        with(binding) {
            tvBack.setOnClickListener {
                if (checkNoteContent()) {
                    childFragmentManager.popBackStack()
                } else {
                    setAndShowWarningDialog("Do you want to proceed?")
                }
            }

            tvSave.setOnClickListener {
                if (checkNoteContent()) {
                    showToast(getString(R.string.saving_note_failed))
                } else {
                    saveNoteContent()
                }
            }
        }
    }

    private fun saveNoteContent() {
        viewModel.saveNote()
    }

    private fun setAndShowWarningDialog(query: String = "") {
        warningDialog.apply {
            setTitle(query)
            setPositiveButton(getString(R.string.save_and_exit)) { d, _ ->
                saveNoteContent()
                d.dismiss()
            }
            setNegativeButton(getString(R.string.exit)) { d, _ ->
                d.dismiss()
                childFragmentManager.popBackStack()
            }
            show()
        }
    }

    private fun observers() {
        with(viewModel) {
            lifecycleScope.launch {
                noteResponse.collectLatest {
                    showToast(it)
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }


    private fun checkNoteContent(): Boolean {
        val noteTitle = binding.etNoteTitle.text ?: ""
        val noteContent = binding.etNoteContent.text ?: ""
        return noteTitle.isEmpty() && noteContent.isEmpty()
    }
}