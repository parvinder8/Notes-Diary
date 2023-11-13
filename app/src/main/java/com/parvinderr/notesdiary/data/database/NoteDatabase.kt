package com.parvinderr.notesdiary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parvinderr.notesdiary.data.dao.NoteDao
import com.parvinderr.notesdiary.data.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
