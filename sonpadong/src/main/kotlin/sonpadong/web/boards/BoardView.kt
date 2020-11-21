package sonpadong.web.boards

import sonpadong.web.boards.categories.Category

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

