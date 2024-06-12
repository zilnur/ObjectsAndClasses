package ru.netology.noteService

data class NoteComment(
    val id: Int,
    val noteId: Int,
    var text: String
)