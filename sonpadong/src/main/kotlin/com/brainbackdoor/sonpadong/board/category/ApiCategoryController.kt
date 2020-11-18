package com.brainbackdoor.sonpadong.board.category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(CATEGORY_BASE_URL)
class ApiCategoryController {
    @Autowired
    private lateinit var categoryService: CategoryService

    @PostMapping
    fun create(@RequestBody view: CategoryCreateView): ResponseEntity<CategoryView> {
        val category = categoryService.create(view)
        return ResponseEntity.created(URI("$CATEGORY_BASE_URL/${category.id}")).build()
    }
}
