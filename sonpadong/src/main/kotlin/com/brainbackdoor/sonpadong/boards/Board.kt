package com.brainbackdoor.sonpadong.boards

import com.brainbackdoor.sonpadong.AutoIncrementEntity
import com.brainbackdoor.sonpadong.boards.categories.Category
import com.brainbackdoor.sonpadong.boards.posts.Post
import javax.persistence.*

@Entity
class Board(
        var title: String,
        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_board_to_category"))
        var category: Category
) : AutoIncrementEntity<Board>() {
    @Column(nullable = false)
    private var position: Int = 0

    init {
        position = category.sizeOfBoards() + 1
    }

    @OneToMany(mappedBy = "board")
    private val posts: MutableList<Post> = mutableListOf()

}
