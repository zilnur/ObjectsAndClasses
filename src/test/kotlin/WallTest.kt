import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallTest {

    @Before
    fun clearWall() {
        Wall.clear()
    }

    @Test
    fun add() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(5,
            4,
            5,
            67,
            7,
            "Test",
            3,
            4,
            false,
            comment,
            attachements = arrayOf(Attachements.FileAttachements(file = File(4,5,"fds","ef")),
                Attachements.AudioAttachements(audio = Audio(3,5,"d","dsa","fsd")))
        )
        val addedPost = Wall.add(post)
        assertEquals(post.copy(id = 0), addedPost)
    }

    @Test
    fun updateTrue() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(0,
            4,
            5,
            67,
            7,
            "NewTest",
            3,
            4,
            false,
            comment,
            attachements = arrayOf(Attachements.FileAttachements(file = File(4,5,"fds","ef")),
                Attachements.AudioAttachements(audio = Audio(3,5,"d","dsa","fsd"))))
        Wall.add(post)
        val result = Wall.update(post)
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(55,
            4,
            5,
            67,
            7,
            "NewTest",
            3,
            4,
            false,
            comment,
            attachements = arrayOf(Attachements.FileAttachements(file = File(4,5,"fds","ef")),
                Attachements.AudioAttachements(audio = Audio(3,5,"d","dsa","fsd"))))
        assertFalse(Wall.update(post))
    }

    @Test
    fun addComment() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(55,
            4,
            5,
            67,
            7,
            "NewTest",
            3,
            4,
            false,
            comment,
            attachements = arrayOf(Attachements.FileAttachements(file = File(4,5,"fds","ef")),
                Attachements.AudioAttachements(audio = Audio(3,5,"d","dsa","fsd"))))
        Wall.add(post)
        val addedComment = Wall.createComment(0,comment)
        assertEquals(comment, addedComment)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(55,
            4,
            5,
            67,
            7,
            "NewTest",
            3,
            4,
            false,
            comment,
            attachements = arrayOf(Attachements.FileAttachements(file = File(4,5,"fds","ef")),
                Attachements.AudioAttachements(audio = Audio(3,5,"d","dsa","fsd"))))
        Wall.add(post)
        Wall.createComment(2, comment)
    }
}