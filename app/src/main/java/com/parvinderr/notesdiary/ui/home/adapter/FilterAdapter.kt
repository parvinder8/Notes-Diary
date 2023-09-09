package com.parvinderr.notesdiary.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.databinding.ItemLayoutFilterBinding
import com.parvinderr.notesdiary.ui.home.adapter.viewholder.FilterViewHolder
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy
import timber.log.Timber

class FilterAdapter(private val onClick: (NotesFilterBy, NotesSortBy) -> Unit) :
    RecyclerView.Adapter<FilterViewHolder>() {
    private val filterList = NotesFilterBy.values()
    private var selectedSortBy = NotesSortBy.ASCENDING
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
        Timber.tag("filter_type").d(item.toString())
        holder.setViews(item, selectedFilter, selectedSortBy)
        holder.itemView.setOnClickListener {
            setSelectedFilter(item, position)

        }
    }

    private fun setSelectedFilter(item: NotesFilterBy, position: Int) {
        val shouldDoPositionUpdate = selectedFilter == item
        val filterType = if (selectedFilter == item) {
            if (NotesSortBy.ASCENDING == selectedSortBy) NotesSortBy.DESCENDING else NotesSortBy.ASCENDING
        } else {
            NotesSortBy.ASCENDING
        }
        selectedFilter = item
        selectedSortBy = filterType
        onClick(
            item, filterType
        )
        if (shouldDoPositionUpdate) notifyItemChanged(position) else notifyDataSetChanged()
    }
}
