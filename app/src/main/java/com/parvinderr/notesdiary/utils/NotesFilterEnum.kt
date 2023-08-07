package com.parvinderr.notesdiary.utils

enum class NotesFilterBy(filterType: String) {
    TITLE("title"), CONTENT("content"), NONE("")
}

enum class NotesSortBy(sortBy: String) {
    ASCENDING("ASC"), DESCENDING("DESC"),
}
