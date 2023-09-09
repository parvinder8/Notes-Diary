package com.parvinderr.notesdiary.utils

enum class NotesFilterBy(private val filterType: String) {
    TITLE("title"), CONTENT("content"), ADDED_DATE("added_date"), UPDATED_DATE("updated_date"), NONE(
        "id"
    );

    fun getFilterTypeValue(): String {
        return filterType
    }
}

enum class NotesSortBy(private val sortBy: String) {
    ASCENDING("ASC"), DESCENDING("DESC");

    fun getSortTypeValue(): String {
        return sortBy
    }
}
