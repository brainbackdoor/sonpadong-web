package com.brainbackdoor.sonpadong.boards.categories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(CATEGORY_BASE_URL)
class ApiCategoryController {
    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping
    fun find(): ResponseEntity<List<CategoryView>> =
            ResponseEntity.ok(categoryService.find())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CategoryView> =
            ResponseEntity.ok(categoryService.findByIdView(id))

    @PostMapping
    fun create(@RequestBody view: CategoryCreateView): ResponseEntity<CategoryView> {
        val category = categoryService.create(view)
        return ResponseEntity.created(URI("$CATEGORY_BASE_URL/${category.id}")).build()
    }
}
