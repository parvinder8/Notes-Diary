package com.parvinderr.notesdiary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,

    @ColumnInfo(name = "title") val noteTitle: String,

    @ColumnInfo(name = "content") val noteContent: String,

    @ColumnInfo(name = "added_date") val addedDate: String,

    @ColumnInfo(name = "added_time") val addedTime: String,

    @ColumnInfo(name = "updated_date") val updatedDate: String,

    @ColumnInfo(name = "updated_time") val updatedTime: String,

    @ColumnInfo(name = "pinned") val isPinned: Boolean,

    @ColumnInfo(name = "is_locked") val isLocked: Boolean = false,

    @ColumnInfo(name = "background_color") val backgroundColor: String
)
