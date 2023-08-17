package com.parvinderr.notesdiary.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.databinding.ItemLayoutNoteBinding
import com.parvinderr.notesdiary.ui.home.adapter.viewholder.NoteViewHolder

class NotesAdapter(private val onClick: (Note, Boolean, View) -> Unit) :
    RecyclerView.Adapter<NoteViewHolder>() {
    private val notes = mutableListOf<Note>()

    fun setData(mData: List<Note>) {
        val diffResult = DiffUtil.calculateDiff(NotesDiffUtilCallback(notes, mData))
        notes.clear()
        notes.addAll(mData)
        diffResult.dispatchUpdatesTo(this)
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
                onClick(item, false, it)
            }

            setOnLongClickListener {
                onClick(item, true, it)
                true
            }
        }
    }


    class NotesDiffUtilCallback(
        private val notes: MutableList<Note>,
        private val mData: List<Note>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return notes.size
        }

        override fun getNewListSize(): Int {
            return mData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return notes[oldItemPosition] == mData[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when {
                notes[oldItemPosition].id == mData[newItemPosition].id && notes[oldItemPosition].noteContent == mData[newItemPosition].noteContent && notes[oldItemPosition].noteTitle == mData[newItemPosition].noteTitle -> true
                else -> false
            }
        }
    }
}