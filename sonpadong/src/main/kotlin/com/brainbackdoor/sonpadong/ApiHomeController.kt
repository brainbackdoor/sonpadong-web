package com.brainbackdoor.sonpadong

import com.brainbackdoor.sonpadong.board.post.POST_LATEST_URL
import com.brainbackdoor.sonpadong.board.post.PostService
import com.brainbackdoor.sonpadong.board.post.PostView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiHomeController {
    @Autowired
    private lateinit var postService: PostService

    @GetMapping(POST_LATEST_URL)
    fun findLatest(): ResponseEntity<List<PostView>> = ResponseEntity.ok(postService.findLatest())
}