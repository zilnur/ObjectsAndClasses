import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import ru.netology.noteService.*

class NoteServiceTest {

    @Before
    fun setUp() {
        NoteService.clearAll()
    }

    @Test
    fun add() {
        assertEquals(1, NoteService.add("qqq", "qqq"))
    }

    @Test
    fun createComment() {
        NoteService.add("qqq", "www")
        assertEquals(1, NoteService.createComment(1, "qqq"))
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentThrows() {
        NoteService.createComment(10, "qqq")
    }

    @Test
    fun delete() {
        NoteService.add("qqq", "qqq")
        assertTrue(NoteService.delete(1))
    }

    @Test
    fun notDelete() {
        assertFalse(NoteService.delete(111))
    }

    @Test
    fun deleteComment() {
        NoteService.add("qqq", "www")
        NoteService.createComment(1, "qqq")
        assertTrue(NoteService.deleteComment(1))
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteCommentThrow() {
        NoteService.deleteComment(15)
    }

    @Test
    fun edit() {
        NoteService.add("qwe", "ewq")
        assertTrue(NoteService.edit(1, "eee", "aaa"))
    }

    @Test
    fun notEdit() {
        assertFalse(NoteService.edit(1, "eee", "eee"))
    }

    @Test
    fun editComment() {
        NoteService.add("qqq", "qqq")
        NoteService.createComment(1, "aaa")
        assertTrue(NoteService.editComment(1, "fff"))
    }

    @Test
    fun notEditComment() {
        assertFalse(NoteService.editComment(1, "rrr"))
    }

    @Test
    fun get() {
        NoteService.add("qqq", "qqq")
        NoteService.add("www", "www")
        val list =listOf(
            Note(
                1,
                "qqq",
                "qqq",
                mutableListOf()
            ),
            Note(
                2,
                "www",
                "www",
                mutableListOf()
            )
        )
        assertEquals(list, NoteService.get(null, null))
    }

    @Test(expected = NoteNotFoundException::class)
    fun getThrows() {
        NoteService.get(arrayOf(5, 1), null)
    }

    @Test
    fun getComments() {
        NoteService.add("qqq", "qqq")
        NoteService.createComment(1, "www")
        NoteService.createComment(1, "eee")
        val list = listOf(
            NoteComment(1, 1, "www"),
            NoteComment(2, 1, "eee")
        )
        assertEquals(list, NoteService.getComments(1))
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsThrows() {
        NoteService.getComments(10)
    }

    @Test
    fun restoreComment() {
        NoteService.add("qqq", "qqq")
        NoteService.createComment(1, "www")
        NoteService.deleteComment(1)
        assertTrue(NoteService.restoreComment(1))
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentThrowsCommentNotFound() {
        NoteService.restoreComment(1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun restoreCommentThrowsNoteNotFound() {
        NoteService.add("qqq", "qqq")
        NoteService.createComment(1, "www")
        NoteService.deleteComment(1)
        NoteService.delete(1)
        NoteService.restoreComment(1)
    }
}