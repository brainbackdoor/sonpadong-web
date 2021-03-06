package sonpadong.web.boards.posts

import sonpadong.web.AutoIncrementEntity
import sonpadong.web.boards.Board
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
) : AutoIncrementEntity<Post>() {
    fun update(update: Post) {
        this.title = update.title
        this.content = update.content
    }
}
