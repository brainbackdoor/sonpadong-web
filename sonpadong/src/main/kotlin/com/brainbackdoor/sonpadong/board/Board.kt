package com.brainbackdoor.sonpadong.board

import com.brainbackdoor.sonpadong.BaseEntity
import com.brainbackdoor.sonpadong.board.post.Post
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Board(
        var title: String
) : BaseEntity<Board>() {

    @OneToMany(mappedBy = "board")
    private val posts: MutableList<Post> = mutableListOf()

}
