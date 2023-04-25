package com.parvinderr.notesdiary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,

    @ColumnInfo(name = "title") val noteTitle: String,

    @ColumnInfo(name = "content") val noteContent: String,

    @ColumnInfo(name = "added_date") val addedDate: Date,

    @ColumnInfo(name = "updated_date") val updatedDate: Date,

    @ColumnInfo(name = "pinned") val isPinned: Boolean,

    @ColumnInfo(name = "background_color") val backgroundColor: String
)
