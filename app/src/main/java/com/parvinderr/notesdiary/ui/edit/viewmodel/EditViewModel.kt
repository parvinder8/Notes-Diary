package com.parvinderr.notesdiary.ui.edit.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parvinderr.notesdiary.R
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.repository.NoteRepository
import com.parvinderr.notesdiary.utils.Constants.DateConstants.Companion.HOUR_MIN_SEC_FORMATTER
import com.parvinderr.notesdiary.utils.Constants.DateConstants.Companion.YEAR_MONTH_FORMATTER
import com.parvinderr.notesdiary.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val notesRepository: NoteRepository,
    private val context: Application,
) : ViewModel() {

    private val _noteTitle = MutableStateFlow("")
    val noteTitle get() = _noteTitle as StateFlow<String>

    private val _noteContent = MutableStateFlow("")
    val noteContent get() = _noteContent as StateFlow<String>

    private val _currentNoteId = MutableStateFlow<Long>(0)
    val currentNoteId get() = _currentNoteId as StateFlow<Long>

    private val _noteResponse = MutableStateFlow("")
    val noteResponse get() = _noteResponse as StateFlow<String>

    private val _isUpdateNote = MutableStateFlow(false)
    val isUpdateNote get() = _isUpdateNote as StateFlow<Boolean>

    private val updatedNote: Note? = null

    val backgroundThread = CoroutineScope(Dispatchers.IO)

    fun setNoteTitle(title: String) {
        val tempTitle = noteTitle.value
        if (tempTitle.trim() == title.trim()) return
        _noteTitle.value = title
    }

    fun setNotesContent(content: String) {
        val tempContent = noteContent.value
        if (tempContent.trim() == content.trim()) return
        _noteContent.value = content
    }

    fun setUpdatedNoteId(id: Long) {
        viewModelScope.launch { _currentNoteId.emit(id) }
    }

    fun setIsUpdateNote(b: Boolean) {
        viewModelScope.launch { _isUpdateNote.emit(b) }
    }

    fun getNoteById(id: Long): Flow<Note> {
        return flow { emit(notesRepository.getNoteById(id)) }
    }

    fun saveNote() {
        val isUpdateNote = isUpdateNote.value
        if (isUpdateNote && updatedNote == null) return
        backgroundThread.launch() {
            saveNoteImpl(isUpdateNote).collectLatest {
                _noteResponse.emit(
                    it
                )
            }
        }
    }


    private fun saveNoteImpl(isUpdateNote: Boolean = false): Flow<String> {
        val tempUpdatedNoteItem = updatedNote
        val noteTitle = _noteTitle.value
        val noteContent = _noteContent.value
        val currentDate = Date().getCurrentDate(YEAR_MONTH_FORMATTER)
        val currentTime = Date().getCurrentDate(HOUR_MIN_SEC_FORMATTER)

        val tempNote = Note(
            noteTitle = noteTitle,
            noteContent = noteContent,
            addedDate = if (isUpdateNote) tempUpdatedNoteItem?.addedDate ?: "" else currentDate,
            addedTime = if (isUpdateNote) tempUpdatedNoteItem?.addedTime ?: "" else currentTime,
            updatedDate = currentDate,
            updatedTime = currentTime,
            isPinned = if (isUpdateNote) tempUpdatedNoteItem?.isPinned ?: false else false,
            isLocked = if (isUpdateNote) tempUpdatedNoteItem?.isLocked ?: false else false,
            backgroundColor = if (isUpdateNote) tempUpdatedNoteItem?.backgroundColor
                ?: "" else "FFF"
        )

        return if (isUpdateNote) {
            updateNoteImpl(tempNote)
        } else {
            saveNoteImpl(tempNote)
        }

    }

    private fun saveNoteImpl(note: Note): Flow<String> {
        return flow {
            try {
                emit(notesRepository.saveNote(note))
            } catch (e: Exception) {
                emit(e.message ?: context.getString(R.string.saving_note_failed))
            }

        }
    }

    private fun updateNoteImpl(note: Note): Flow<String> {
        return flow {
            try {
                emit(notesRepository.updateNote(note))
            } catch (e: Exception) {
                emit(e.message ?: context.getString(R.string.saving_note_failed))
            }
        }
    }


}