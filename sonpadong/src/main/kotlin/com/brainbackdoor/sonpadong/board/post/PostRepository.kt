package com.brainbackdoor.sonpadong.board.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findTop5ByOrderByCreatedDateDesc(): List<Post>
}
