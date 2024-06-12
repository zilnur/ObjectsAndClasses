package ru.netology.noteService

data class Note(
    val id: Int,
    var title: String,
    var text: String,
    var comments: MutableList<Int>
    )