package com.parvinderr.notesdiary.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.common.ViewBindingFragment
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.databinding.BottomSheetLayoutBinding
import com.parvinderr.notesdiary.databinding.FragmentHomeBinding
import com.parvinderr.notesdiary.preference.SettingPreferenceHelper
import com.parvinderr.notesdiary.ui.home.adapter.FilterAdapter
import com.parvinderr.notesdiary.ui.home.adapter.NotesAdapter
import com.parvinderr.notesdiary.ui.home.viewmodel.HomeViewModel
import com.parvinderr.notesdiary.utils.Constants
import com.parvinderr.notesdiary.utils.LayoutEnum
import com.parvinderr.notesdiary.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val notesAdapter = NotesAdapter { item, isLongPressed, itemView ->
        if (isLongPressed) {
            showPopUpMenu(item, itemView)
        } else {
            navigateToEditNote(item.id)
        }
    }

    private val filterAdapter = FilterAdapter { filterBy, sortBy ->
        Timber.tag("selected_filter").d("$filterBy --> $sortBy")
        homeViewModel.apply {
            setSortAndFilterType(sortBy, filterBy)
        }
    }

    private fun navigateToEditNote(id: Long) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(
                id, true
            )
        )
    }

    private fun showPopUpMenu(item: Note, itemView: View) {
        val menu = PopupMenu(requireContext(), itemView)
        menu.inflate(R.menu.note_item_menu)
        menu.setOnMenuItemClickListener {
            when (it.title.toString().lowercase().trim()) {
                "edit" -> {
                    navigateToEditNote(item.id)
                    true
                }

                "delete" -> {
                    lifecycleScope.launch {
                        homeViewModel.deleteNote(item).collectLatest { response ->
                            if (response.isEmpty()) return@collectLatest
                            showToast(response)
                            getNotesData()
                        }
                    }
                    true
                }

                else -> false
            }
        }
        menu.show()
    }

    private var job: Job? = null

    private val bottomSheet by lazy {
        BottomSheetDialog(requireContext())
    }


    override fun init() {
        clickListeners()
        observers()
        listeners()
    }

    override fun onResume() {
        setLayoutManager()
        super.onResume()
    }

    private fun setLayoutManager() {
        val layoutManager = SettingPreferenceHelper.preference.getLayoutPreference()
        val tempLayoutManager = if (layoutManager == LayoutEnum.GRID) GridLayoutManager(
            requireContext(), 2, GridLayoutManager.VERTICAL, false
        ) else LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNotes.layoutManager = tempLayoutManager
        binding.rvNotes.scrollToPosition(tempLayoutManager.findFirstCompletelyVisibleItemPosition())
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
            fbAdd.setOnClickListener {
                val navDirection =
                    HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(-1L, false)
                findNavController().navigate(navDirection)
            }
            tvFilter.setOnClickListener {
                showFilterBottomSheet()
            }
        }
    }

    private fun observers() {
        lifecycleScope.launch {
            homeViewModel.allNotes.collectLatest {
                binding.rvNotes.adapter = notesAdapter
                notesAdapter.setData(it)

            }
        }

        lifecycleScope.launch {
            homeViewModel.sortType.collectLatest {
                getNotesData(homeViewModel.searchQuery.value ?: "")
            }
        }


        lifecycleScope.launch {
            homeViewModel.searchQuery.collectLatest {
                Timber.tag("search_query").d(it)
                job?.cancel()
                job = lifecycleScope.launch {
                    delay(Constants.SearchConstants.SEARCH_DEBOUNCING_TIME)
                    getNotesData(it)
                }
            }
        }


    }

    override fun onBind(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }


    private fun getNotesData(q: String = "") {
        val query = q.ifEmpty { homeViewModel.searchQuery.value }
        val filterType = homeViewModel.filterType.value
        val sortType = homeViewModel.sortType.value
        homeViewModel.getNotesData(false, query, filterType, sortType)
    }

    private fun showFilterBottomSheet() {
        val bottomSheetBinding = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetBinding.recyclerView.adapter = filterAdapter
        bottomSheet.setContentView(bottomSheetBinding.root)
        bottomSheet.show()
    }

}
