package com.parvinderr.notesdiary.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.databinding.ItemLayoutFilterBinding
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy

class FilterViewHolder(private val binding: ItemLayoutFilterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setViews(item: NotesFilterBy, selectedFilter: NotesFilterBy, selectedSortBy: NotesSortBy) {

    }
}