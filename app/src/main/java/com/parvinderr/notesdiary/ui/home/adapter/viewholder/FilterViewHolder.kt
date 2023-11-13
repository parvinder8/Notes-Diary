package com.parvinderr.notesdiary.ui.home.adapter.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.databinding.ItemLayoutFilterBinding
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy

class FilterViewHolder(private val binding: ItemLayoutFilterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setViews(item: NotesFilterBy, selectedFilter: NotesFilterBy, selectedSortBy: NotesSortBy) {
        if (selectedFilter == item) {
            binding.tvFilterName.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                when (selectedSortBy) {
                    NotesSortBy.DESCENDING -> {
                        ContextCompat.getDrawable(binding.root.context, R.drawable.ic_arrow_down)
                    }

                    NotesSortBy.ASCENDING -> {
                        ContextCompat.getDrawable(binding.root.context, R.drawable.ic_arrow_up)
                    }

                    else -> {
                        null
                    }
                },
                null,
            )
        } else {
            binding.tvFilterName.setCompoundDrawables(null, null, null, null)
        }
        binding.tvFilterName.text = item.name
    }
}
