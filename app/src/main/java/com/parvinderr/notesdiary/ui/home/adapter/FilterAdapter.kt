package com.parvinderr.notesdiary.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.databinding.ItemLayoutFilterBinding
import com.parvinderr.notesdiary.ui.home.adapter.viewholder.FilterViewHolder
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy

class FilterAdapter(private val onClick: (NotesFilterBy, NotesSortBy) -> Unit) :
    RecyclerView.Adapter<FilterViewHolder>() {
    private val filterList = NotesFilterBy.values()
    private var selectedSortBy = NotesSortBy.DESCENDING
    private var selectedFilter = NotesFilterBy.NONE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemLayoutFilterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = filterList[position]
        holder.setViews(item, selectedFilter, selectedSortBy)
        holder.itemView.setOnClickListener {
            if (selectedFilter == item) {
                onClick(
                    item,
                    if (NotesSortBy.ASCENDING == selectedSortBy) NotesSortBy.DESCENDING else NotesSortBy.ASCENDING
                )
            }
        }
    }
}