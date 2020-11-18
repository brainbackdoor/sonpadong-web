package com.brainbackdoor.sonpadong.board.category

import com.brainbackdoor.sonpadong.BaseEntity
import com.brainbackdoor.sonpadong.board.Board
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Category(
        val title: String
) : BaseEntity<Category>() {
    @OneToMany(mappedBy = "category")
    private val boards: MutableList<Board> = mutableListOf()
}
