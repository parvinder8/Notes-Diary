package com.parvinderr.notesdiary.repository

import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy

interface NoteRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun filterNotes(
        searchQuery: String,
        filter: NotesFilterBy,
        listType: NotesSortBy
    ): List<Note>

    suspend fun searchNotes(q: String): List<Note>

    suspend fun saveNote(note: Note): String

    suspend fun updateNote(note: Note): String

    suspend fun getNoteById(id: Long): Note
}