import java.net.CookieStore

data class Post(
    val id: Int,
    val ownerId: Int,
    val formId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String?,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Comment?,
    val attachements: Array<Attachements>?
)