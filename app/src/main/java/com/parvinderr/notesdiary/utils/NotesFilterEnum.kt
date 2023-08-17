package com.parvinderr.notesdiary.utils

enum class NotesFilterBy(filterType: String) {
    TITLE("title"), CONTENT("content"), ADDED_DATE("added_date"), UPDATED_DATE("updated_date"), NONE(
        "id"
    ),
}

enum class NotesSortBy(sortBy: String) {
    ASCENDING("ASC"), DESCENDING("DESC"),
}
