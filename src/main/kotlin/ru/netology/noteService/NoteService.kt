package ru.netology.noteService

public object NoteService {
    private var notes: MutableList<Note> = mutableListOf()
    private var comments: MutableList<NoteComment> = mutableListOf()
    private var lastNoteId = 0
    private var lastCommentId = 0

    public fun add(title: String, text: String): Int {
        lastNoteId += 1
        val note = Note(lastNoteId, title, text, mutableListOf())
        notes.add(note)
        return  lastNoteId
    }

    public fun createComment(noteId: Int, message: String): Int {
        val index = notes.indexOfFirst { it.id == noteId }
        if (index != -1) {
            lastCommentId += 1
            val comment = NoteComment(lastCommentId,noteId ,message)
            notes[index].comments.add(comment.id)
            comments.add(comment)
            return  lastCommentId
        } else {
            throw NoteNotFoundException("(NoteService.createComment). Заметки с таким id не найдено")
        }
    }

    public fun delete(id: Int): Boolean {
        return notes.removeIf { it.id == id }
    }

    public fun deleteComment(commentId: Int): Boolean {
        val index = notes.indexOfFirst { it.comments.contains(commentId) }
        if (index != -1) {
            return notes[index].comments.removeIf { it == commentId }
        } else {
            throw NoteNotFoundException("(NoteService.deleteComment) Не найдена заметка c данным комментарием")
        }
    }

    public fun edit(noteId: Int, title: String, text: String): Boolean {
        val index = notes.indexOfFirst { it.id == noteId }
        if (index != -1) {
            notes[index].title = title
            notes[index].text = text
            return true
        } else {
            return false
        }
    }

    public fun editComment(commentId: Int, message: String): Boolean {
        val index = comments.indexOfFirst { it.id == commentId }
        if (index != -1) {
            comments[index].text = message
            return true
        } else {
            return  false
        }
    }

    public fun get(noteIds: Array<Int>?, count: Int?): List<Note> {
        var result = mutableListOf<Note>()
        if (noteIds != null) {
            noteIds.forEach {
                val id = it
                val note = notes.firstOrNull {
                    it.id == id
                }
                if (note != null) {
                    result.add(note)
                } else {
                    throw  NoteNotFoundException("(NoteService.get) Не найдена заметка по даннмоу ID")
                }
            }
        } else {
            result = notes
        }

        if (count != null) {
            result = result.subList(0, count-1)
        }
        return  result
    }

    public fun getComments(noteId: Int): List<NoteComment> {
        var result = mutableListOf<NoteComment>()
        val note = notes.firstOrNull { it.id == noteId}
        if (note != null) {
            val commentIds = note.comments
            commentIds.forEach {
                val id = it
                val comment = comments.first {
                    it.id == id
                }
                result.add(comment)
            }
        } else {
            throw NoteNotFoundException("(NoteService.getComment) Не найдена заметка по даннмоу ID")
        }
        return  result
    }

    public fun restoreComment(commentId: Int): Boolean {
        val comment = comments.firstOrNull { it.id == commentId }
        if (comment != null) {
            val noteId = comment.noteId
            val index = notes.indexOfFirst { it.id == noteId }
            if (index != -1) {
                notes[index].comments.add(commentId)
                return true
            } else {
                throw NoteNotFoundException("(NoteService.restoreComment) Не найдена заметка по даннмоу ID")
            }
        } else {
            throw CommentNotFoundException("(NoteService.restoreComment) Не найден комментарий по данному ID")
        }
    }

    public fun clearAll() {
        notes.clear()
        comments.clear()
        lastNoteId = 0
        lastCommentId = 0
    }
}
