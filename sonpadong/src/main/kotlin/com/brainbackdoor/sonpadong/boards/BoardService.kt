package com.brainbackdoor.sonpadong.boards

import com.brainbackdoor.sonpadong.ResourceNotFoundException
import com.brainbackdoor.sonpadong.boards.categories.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BoardService @Autowired constructor(
        private var boardRepository: BoardRepository,
        private var categoryService: CategoryService
) {
    fun find(): List<BoardView> = toBoardViews(boardRepository.findAll())

    fun findById(id: Long): Board = boardRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("$id 에 해당하는 게시판이 없습니다") }

    fun create(view: BoardCreateView): BoardView {
        val category = categoryService.findById(view.categoryId)
        return toBoardView(boardRepository.save(view.toBoard(category)))
    }
}
