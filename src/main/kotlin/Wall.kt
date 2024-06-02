object Wall {

    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var lastId = 0

    fun add(post: Post): Post {
        val newPost = post.copy(id = lastId)
        posts += newPost
        lastId += 1
        return newPost
    }

    fun update(post: Post): Boolean {
        var result = false
        posts.withIndex().forEach { (index, value) ->
            print(value.id)
            if (value.id == post.id) {
                posts[index] = post
                result = true
            }
        }
        return result
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

     fun createComment(postId: Int, comment: Comment): Comment {
         if (posts.any {it.id == postId}) {
             comments += comment
             return comment
         } else {
             throw PostNotFoundException("Не найдет указанный пост")
         }
     }
}