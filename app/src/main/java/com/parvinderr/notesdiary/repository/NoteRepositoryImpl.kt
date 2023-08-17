package com.parvinderr.notesdiary.repository

import android.content.Context
import android.util.Log
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.data.dao.NoteDao
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val context: Context, private val notesDao: NoteDao
) : NoteRepository {
    override suspend fun getAllNotes(): List<Note> {
        try {
            return notesDao.getAll()
        } catch (e: Exception) {
            throw Exception(e.message ?: context.getString(R.string.something_went_wrong))
        }
    }

    override suspend fun filterNotes(
        searchQuery: String, filter: NotesFilterBy, listType: NotesSortBy
    ): List<Note> {
        try {
            return notesDao.filterNotes(searchQuery, filter.name, listType.name)
        } catch (e: Exception) {
            throw Exception(e.message ?: context.getString(R.string.something_went_wrong))
        }
    }

    override suspend fun searchNotes(q: String): List<Note> {
        try {
            return notesDao.searchNotes(q)
        } catch (e: Exception) {
            throw Exception(e.message ?: context.getString(R.string.something_went_wrong))
        }
    }

    override suspend fun saveNote(note: Note): String {
        Log.d("repo_operations", note.toString())
        try {
            Log.d("repo_operations", "in try")

            notesDao.insert(note)
            return context.getString(R.string.note_saved_successfully)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e.message ?: context.getString(R.string.saving_note_failed))
        }
    }

    override suspend fun updateNote(note: Note): String {
        try {
            notesDao.update(note)
            return context.getString(R.string.note_updated_successfully)
        } catch (e: Exception) {
            throw Exception(e.message ?: context.getString(R.string.updating_note_failed))
        }
    }

    override suspend fun getNoteById(id: Long): Note {
        try {
            return notesDao.getById(id)
        } catch (e: Exception) {
            throw Exception(e.message ?: context.getString(R.string.something_went_wrong))
        }

    }

    override suspend fun deleteNote(note: Note): String {
        try {
            notesDao.delete(note)
            return context.getString(R.string.note_deleted_successfully)
        } catch (e: Exception) {
            throw Exception(e.message ?: context.getString(R.string.deleting_note_failed))
        }
    }

}