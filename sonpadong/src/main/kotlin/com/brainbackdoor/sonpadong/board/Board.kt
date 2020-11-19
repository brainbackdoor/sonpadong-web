package com.brainbackdoor.sonpadong.board

import com.brainbackdoor.sonpadong.BaseEntity
import com.brainbackdoor.sonpadong.board.category.Category
import com.brainbackdoor.sonpadong.board.post.Post
import javax.persistence.*

@Entity
class Board(
        var title: String,
        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_board_to_category"))
        var category: Category
) : BaseEntity<Board>() {
    @Column(nullable = false)
    private var position: Int = 0

    init {
        position = category.sizeOfBoards() + 1
    }

    @OneToMany(mappedBy = "board")
    private val posts: MutableList<Post> = mutableListOf()

}
