object Wall {

    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        println("Add")
        val newPost = post.copy(id = lastId)
        posts += newPost
        lastId += 1
        return newPost
    }

    fun update(post: Post): Boolean {
        var result = false
        println("Update")
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
}