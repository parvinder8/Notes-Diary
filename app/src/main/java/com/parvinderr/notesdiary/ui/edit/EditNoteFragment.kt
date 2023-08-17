package com.parvinderr.notesdiary.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentEditNoteBinding
import com.parvinderr.notesdiary.ui.edit.viewmodel.EditViewModel
import com.parvinderr.notesdiary.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class EditNoteFragment @Inject constructor() : ViewBindingFragment<FragmentEditNoteBinding>() {

    private val viewModel by viewModels<EditViewModel>()

    private val warningDialog by lazy {
        AlertDialog.Builder(requireContext())
    }

    override fun onBind(inflater: LayoutInflater, container: ViewGroup?): FragmentEditNoteBinding {
        return FragmentEditNoteBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setViews()
        observers()
        clickListeners()
        listeners()
    }

    private fun setViews() {
        binding.tvDateTimeHolder.text =
            SimpleDateFormat("EEEE, MMM/dd, HH:mm a", Locale.ENGLISH).format(Date())
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
                    navigateToHome()
                } else {
                    setAndShowWarningDialog("Do you want to proceed?")
                }
            }

            tvSave.setOnClickListener {
                if (checkNoteContent()) {
                    showToast(getString(R.string.title_and_content_empty))
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
                navigateToHome()
            }
            show()
        }
    }

    private fun observers() {
        with(viewModel) {
            lifecycleScope.launch {
                noteResponse.collectLatest {
                    if (it.isEmpty()) return@collectLatest
                    showToast(it)
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment())
    }


    private fun checkNoteContent(): Boolean {
        val noteTitle = binding.etNoteTitle.text ?: ""
        val noteContent = binding.etNoteContent.text ?: ""
        return noteTitle.isEmpty() && noteContent.isEmpty()
    }
}