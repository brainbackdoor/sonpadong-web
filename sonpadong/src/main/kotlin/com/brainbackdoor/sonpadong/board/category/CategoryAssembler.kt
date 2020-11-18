package com.brainbackdoor.sonpadong.board.category

fun toCategoryView(category: Category): CategoryView = CategoryView(
        category.id,
        category.title
)
