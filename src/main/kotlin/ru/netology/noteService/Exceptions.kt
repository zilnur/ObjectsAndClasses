package ru.netology.noteService

class NoteNotFoundException(message: String): RuntimeException(message)

class CommentNotFoundException(message: String): RuntimeException(message)