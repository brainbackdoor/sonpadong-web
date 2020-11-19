package com.brainbackdoor.sonpadong.board.category

fun toCategoryViews(categories: List<Category>): List<CategoryView> = categories.map { toCategoryView(it) }

fun toCategoryView(category: Category): CategoryView = CategoryView(
        category.id,
        category.title
)
