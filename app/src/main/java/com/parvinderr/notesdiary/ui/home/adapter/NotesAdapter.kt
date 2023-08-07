package com.parvinderr.notesdiary.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.databinding.ItemLayoutNoteBinding
import com.parvinderr.notesdiary.ui.home.adapter.viewholder.NoteViewHolder

class NotesAdapter(private val onClick: (Note, Boolean) -> Unit) :
    RecyclerView.Adapter<NoteViewHolder>() {
    private val notes = mutableListOf<Note>()

    fun setData(mData: List<Note>) {
        notes.apply {
            clear()
            addAll(mData)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemLayoutNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = notes[position]
        holder.setViews(item)
        holder.itemView.apply {
            setOnClickListener {
                onClick(item, false)
            }

            setOnLongClickListener {
                onClick(item, true)
                true
            }
        }
    }
}