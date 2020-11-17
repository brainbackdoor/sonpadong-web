package com.brainbackdoor.sonpadong.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(BOARD_BASE_URL)
class ApiBoardController {
    @Autowired
    private lateinit var boardService: BoardService

    @GetMapping
    fun find() : ResponseEntity<List<BoardView>> = ResponseEntity.ok(toBoardViews(boardService.find()))

    @PostMapping
    fun create(@RequestBody view: BoardCreateView) : ResponseEntity<BoardView> {
        val board = boardService.create(view)

        return ResponseEntity.created(URI("$BOARD_BASE_URL/${board.id}")).build()
    }
}
