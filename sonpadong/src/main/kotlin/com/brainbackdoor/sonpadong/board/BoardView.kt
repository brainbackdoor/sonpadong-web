package com.brainbackdoor.sonpadong.board

import com.brainbackdoor.sonpadong.board.category.Category

const val BOARD_BASE_URL = "/api/boards"

class BoardCreateView(
        val categoryId: Long,
        val title: String
) {
    fun toBoard(category: Category): Board = Board(
            title,
            category
    )
}

class BoardView(
        val id: Long,
        val title: String
)

