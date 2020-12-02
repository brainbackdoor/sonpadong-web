package sonpadong.web.boards.posts

import sonpadong.web.boards.Board

const val POST_BASE_URL = "/api/posts"
const val POST_LATEST_URL = "/api/posts-latest"

data class PostCreateRequest(
        val boardId: Long,

        val title: String,
        val content: String
) {
    fun toPost(board: Board): Post = Post(
            title,
            content,
            board
    )
}

class PostUpdateView(
        val boardId: Long,
        val title: String,
        val content: String
) {
    fun toPost(board: Board): Post = Post(
            title,
            content,
            board
    )
}

class PostView(
        val id: Long,
        val title: String,
        val content: String,
        val createdDate: String
)
