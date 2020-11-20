package com.brainbackdoor.sonpadong.boards.categories

import com.brainbackdoor.sonpadong.AutoIncrementEntity
import com.brainbackdoor.sonpadong.boards.Board
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.OrderBy

@Entity
class Category(
        val title: String
) : AutoIncrementEntity<Category>() {

    @OneToMany(mappedBy = "category", cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @OrderBy("position ASC")
    private val boards: MutableList<Board> = mutableListOf()

    fun sizeOfBoards() = boards.size


}
