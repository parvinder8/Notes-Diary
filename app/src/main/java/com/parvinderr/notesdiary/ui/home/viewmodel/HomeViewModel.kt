package com.parvinderr.notesdiary.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parvinderr.notesdiary.data.model.Note
import com.parvinderr.notesdiary.repository.NoteRepository
import com.parvinderr.notesdiary.utils.NotesFilterBy
import com.parvinderr.notesdiary.utils.NotesSortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val notesRepository: NoteRepository
) : ViewModel() {

    private val _allNotes = MutableStateFlow<List<Note>>(ArrayList())

    val allNotes get() = _allNotes as StateFlow<List<Note>>

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery as StateFlow<String>

    private val _filterType = MutableStateFlow(NotesFilterBy.NONE)
    val filterType get() = _filterType as StateFlow<NotesFilterBy>

    private val _sortType = MutableStateFlow(NotesSortBy.ASCENDING)
    val sortType get() = _sortType as StateFlow<NotesSortBy>

    init {
        getNotesData()
    }


    private fun getAll(): Flow<List<Note>> = flow {
        val tempAllNotes = notesRepository.getAllNotes()
        Log.d("in_get_all", tempAllNotes.toString())
        emit(tempAllNotes)
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = ArrayList()
    )

    private fun filterNotes(
        searchQuery: String, filterType: NotesFilterBy, sortBy: NotesSortBy
    ): Flow<List<Note>> {

        return flow {
            val tempAllNotes = notesRepository.filterNotes(searchQuery, filterType, sortBy)
            Log.d("in_filter", tempAllNotes.toString())

            emit(tempAllNotes)
        }.stateIn(
            scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = ArrayList()
        )
    }


    fun getNotesData(
        getAllData: Boolean = true,
        searchQuery: String = "",
        filterType: NotesFilterBy = NotesFilterBy.NONE,
        sortBy: NotesSortBy = NotesSortBy.ASCENDING
    ) {
        viewModelScope.launch {
            _allNotes.emitAll(
                if (getAllData) getAll() else filterNotes(
                    searchQuery, filterType, sortBy
                )
            )
        }
    }


    fun setSearchQuery(q: String) {
        viewModelScope.launch { _searchQuery.emit(q) }
    }


    fun deleteNote(note: Note): Flow<String> {
        return flow { emit(notesRepository.deleteNote(note)) }

    }

}