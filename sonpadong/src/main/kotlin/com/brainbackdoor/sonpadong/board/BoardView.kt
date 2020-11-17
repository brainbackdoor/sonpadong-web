package com.brainbackdoor.sonpadong.board

const val BOARD_BASE_URL = "/api/boards"

class BoardCreateView(
        val title: String
) {
    fun toBoard(): Board = Board(
            title
    )
}

class BoardView(
        val title: String
)

