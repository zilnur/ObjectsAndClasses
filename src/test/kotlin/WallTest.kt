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
        val post = Post(5,4,5,67,7,"Test", 3,4,false, comment)
        val addedPost = Wall.add(post)
        assertEquals(post.copy(id = 0), addedPost)
    }

    @Before
    fun clearWall2() {
        Wall.clear()
    }

    @Test
    fun updateTrue() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(0,4,5,67,7,"NewTest", 3,4,false, comment)
        Wall.add(post)
        val result = Wall.update(post)
        assertTrue(result)
    }

    @Before
    fun clearWall3() {
        Wall.clear()
    }

    @Test
    fun updateFalse() {
        val comment = Comment(1,true, false, false, false)
        val post = Post(55,4,5,67,7,"NewTest", 3,4,false, comment)
        assertFalse(Wall.update(post))
    }
}