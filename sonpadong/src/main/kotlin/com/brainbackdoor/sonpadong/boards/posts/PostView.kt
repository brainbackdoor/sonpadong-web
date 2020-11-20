package com.brainbackdoor.sonpadong.boards.posts

import com.brainbackdoor.sonpadong.boards.Board

const val POST_BASE_URL = "/api/posts"
const val POST_LATEST_URL = "/api/posts-latest"

class PostCreateView(
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
