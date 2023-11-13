package com.parvinderr.notesdiary.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.databinding.ItemLayoutNoteBinding
import com.parvinderr.notesdiary.utils.gone
import com.parvinderr.notesdiary.utils.visible

class NoteViewHolder(private val binding: ItemLayoutNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setViews(item: Note) {
        binding.apply {
            if (item.noteTitle.isEmpty()) {
                tvNoteTitle.gone()
            } else {
                tvNoteTitle.visible()
            }
            tvNoteTitle.text = item.noteTitle.substring(
                0,
                if (item.noteTitle.length > 40) {
                    40
                } else {
                    item.noteTitle.length
                },
            )
            tvNoteContent.text = item.noteContent.substring(
                0,
                if (item.noteContent.length > 150) {
                    150
                } else {
                    item.noteContent.length
                },
            )
            tvNoteTimeline.text = item.updatedDate
        }
    }
}
