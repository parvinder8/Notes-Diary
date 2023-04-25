package com.parvinderr.notesdiary.repository

import com.parvinderr.notesdiary.data.dao.NoteDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(val notesDao: NoteDao) {

}