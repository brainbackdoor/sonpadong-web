package com.brainbackdoor.sonpadong.board.category

import com.brainbackdoor.sonpadong.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService @Autowired constructor(
        private var categoryRepository: CategoryRepository
) {
    fun findById(id: Long): Category = categoryRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("$id 에 해당하는 카테고리가 없습니다") }

    fun create(view: CategoryCreateView): CategoryView {
        return toCategoryView(categoryRepository.save(view.toCategory()))
    }
}