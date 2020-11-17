package com.brainbackdoor.sonpadong.board.post

import com.brainbackdoor.sonpadong.BaseEntity
import com.brainbackdoor.sonpadong.board.Board
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Post(
        var title: String,
        var content: String,

        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_post_to_board"))
        var board: Board
) : BaseEntity<Post>() {
    fun update(update: Post) {
        this.title = update.title
        this.content = update.content
    }
}
