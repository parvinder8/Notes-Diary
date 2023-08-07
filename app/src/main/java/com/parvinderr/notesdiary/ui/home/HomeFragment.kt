package com.parvinderr.notesdiary.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.databinding.FragmentHomeBinding
import com.parvinderr.notesdiary.ui.home.adapter.NotesAdapter
import com.parvinderr.notesdiary.ui.home.viewmodel.HomeViewModel
import com.parvinderr.notesdiary.utils.Constants.SearchConstant.Companion.SEARCH_DEBOUNCING_TIME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val notesAdapter = NotesAdapter { item, isLongPressed ->

        if (isLongPressed) {
            /**
             * show menu on long press
             */
        } else {
            /**
             * move to edit note screen
             */
        }
    }

    private val job = lifecycleScope.launch {
        delay(SEARCH_DEBOUNCING_TIME)
        homeViewModel.getNotesData(false)
    }

    private val bottomSheet by lazy {
        BottomSheetDialog(requireContext())
    }


    override fun init() {
        Log.d("fragment_data", "init called")
        clickListeners()
        observers()
        listeners()
    }

    private fun listeners() {
        with(binding) {
            etSearch.addTextChangedListener {
                it?.toString()?.let { q ->
                    homeViewModel.setSearchQuery(q)
                }
            }
        }
    }

    private fun clickListeners() {
        with(binding) {

        }
    }

    private fun observers() {
        lifecycleScope.launch {
            homeViewModel.allNotes.collectLatest {
                notesAdapter.setData(it)
            }

            homeViewModel.searchQuery.collectLatest {

            }

        }
    }

    override fun onBind(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

}