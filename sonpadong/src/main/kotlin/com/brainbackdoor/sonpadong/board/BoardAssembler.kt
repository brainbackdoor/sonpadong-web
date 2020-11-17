package com.brainbackdoor.sonpadong.board

fun toBoardViews(boards:List<Board>) : List<BoardView> = boards.map { toBoardView(it) }

fun toBoardView(board: Board) : BoardView = BoardView(
        board.title
)
