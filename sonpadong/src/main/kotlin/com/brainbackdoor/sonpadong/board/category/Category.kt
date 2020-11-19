package com.brainbackdoor.sonpadong.board.category

import com.brainbackdoor.sonpadong.BaseEntity
import com.brainbackdoor.sonpadong.board.Board
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.OrderBy

@Entity
class Category(
        val title: String
) : BaseEntity<Category>() {

    @OneToMany(mappedBy = "category", cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @OrderBy("position ASC")
    private val boards: MutableList<Board> = mutableListOf()

    fun sizeOfBoards() = boards.size


}
