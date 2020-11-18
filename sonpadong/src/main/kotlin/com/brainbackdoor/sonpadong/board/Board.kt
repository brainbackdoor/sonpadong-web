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

    @OneToMany(mappedBy = "board")
    private val posts: MutableList<Post> = mutableListOf()

}
