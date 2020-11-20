package com.brainbackdoor.sonpadong.boards.categories

fun toCategoryViews(categories: List<Category>): List<CategoryView> = categories.map { toCategoryView(it) }

fun toCategoryView(category: Category): CategoryView = CategoryView(
        category.id,
        category.title
)
