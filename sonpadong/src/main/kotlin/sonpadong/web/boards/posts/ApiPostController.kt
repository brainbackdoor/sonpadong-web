package sonpadong.web.boards.posts

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping(POST_BASE_URL)
class ApiPostController {
    @Autowired
    private lateinit var postService: PostService

    @GetMapping
    fun find(): ResponseEntity<List<PostView>> =
            ResponseEntity.ok(postService.find())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<PostView> =
            ResponseEntity.ok(postService.findViewById(id))

    @PostMapping
    fun create(@RequestBody request: PostCreateRequest): ResponseEntity<PostView> {
        val post = postService.create(request)

        return ResponseEntity.created(URI("$POST_BASE_URL/${post.id}")).build()
    }

    @PutMapping("/{id}")
    fun update(@RequestBody view: PostUpdateView, @PathVariable id: Long): ResponseEntity<PostView> =
            ResponseEntity.ok(postService.update(id, view))
}
