package sonpadong.web.boards

import sonpadong.web.AutoIncrementEntity
import sonpadong.web.boards.categories.Category
import sonpadong.web.boards.posts.Post
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
