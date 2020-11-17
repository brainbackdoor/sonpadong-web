package com.brainbackdoor.sonpadong.board

import com.brainbackdoor.sonpadong.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BoardService @Autowired constructor(
        private var boardRepository: BoardRepository
) {
    fun find(): List<Board> = boardRepository.findAll()

    fun findById(id: Long): Board = boardRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("$id 에 해당하는 게시판이 없습니다") }

    fun create(view: BoardCreateView): Board = boardRepository.save(view.toBoard())
}
