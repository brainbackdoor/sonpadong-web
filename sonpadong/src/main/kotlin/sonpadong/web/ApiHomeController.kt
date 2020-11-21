package sonpadong.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import sonpadong.web.boards.posts.POST_LATEST_URL
import sonpadong.web.boards.posts.PostService
import sonpadong.web.boards.posts.PostView

@RestController
class ApiHomeController {
    @Autowired
    private lateinit var postService: PostService

    @GetMapping(POST_LATEST_URL)
    fun findLatest(): ResponseEntity<List<PostView>> = ResponseEntity.ok(postService.findLatest())
}
